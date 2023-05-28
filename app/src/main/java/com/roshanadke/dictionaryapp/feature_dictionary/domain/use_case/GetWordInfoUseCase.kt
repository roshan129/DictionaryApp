package com.roshanadke.dictionaryapp.feature_dictionary.domain.use_case

import com.roshanadke.dictionaryapp.core.util.Resource
import com.roshanadke.dictionaryapp.feature_dictionary.domain.models.WordInfo
import com.roshanadke.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word = word)
    }

}