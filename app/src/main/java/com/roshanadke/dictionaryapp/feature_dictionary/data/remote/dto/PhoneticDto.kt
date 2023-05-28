package com.roshanadke.dictionaryapp.feature_dictionary.data.remote.dto

import com.roshanadke.dictionaryapp.feature_dictionary.domain.models.Phonetic

data class PhoneticDto(
    val audio: String,
    val sourceUrl: String,
    val text: String
) {
    fun toPhonetic(): Phonetic {
        return Phonetic(
            audio = audio,
            sourceUrl = sourceUrl,
            text = text,
        )
    }
}