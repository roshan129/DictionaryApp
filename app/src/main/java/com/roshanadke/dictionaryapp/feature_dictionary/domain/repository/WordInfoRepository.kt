package com.roshanadke.dictionaryapp.feature_dictionary.domain.repository

import com.roshanadke.dictionaryapp.core.util.Resource
import com.roshanadke.dictionaryapp.feature_dictionary.domain.models.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}