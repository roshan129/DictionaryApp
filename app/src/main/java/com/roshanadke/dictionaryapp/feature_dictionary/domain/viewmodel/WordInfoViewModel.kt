package com.roshanadke.dictionaryapp.feature_dictionary.domain.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshanadke.dictionaryapp.core.util.Resource
import com.roshanadke.dictionaryapp.feature_dictionary.domain.use_case.GetWordInfoUseCase
import com.roshanadke.dictionaryapp.feature_dictionary.presentation.WordInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfoUseCase: GetWordInfoUseCase
): ViewModel() {

    private var _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private var _wordInfoState = mutableStateOf(WordInfoState())
    val wordInfoState: State<WordInfoState> = _wordInfoState

    private var _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfoUseCase.invoke(word = query)
                .onEach {result ->
                    when(result) {
                        is Resource.Success -> {
                            _wordInfoState.value = _wordInfoState.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _wordInfoState.value = _wordInfoState.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.showSnackBar(
                                message = result.message ?: "Unknown Error"
                            ))
                        }
                        is Resource.Loading -> {
                            _wordInfoState.value = _wordInfoState.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }

                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class showSnackBar(val message: String): UIEvent()
    }

}