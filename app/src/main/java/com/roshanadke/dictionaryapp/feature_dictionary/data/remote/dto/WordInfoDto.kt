package com.roshanadke.dictionaryapp.feature_dictionary.data.remote.dto

import com.roshanadke.dictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity
import com.roshanadke.dictionaryapp.feature_dictionary.domain.models.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
) {

    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            sourceUrls = sourceUrls,
            word = word,
        )
    }
}