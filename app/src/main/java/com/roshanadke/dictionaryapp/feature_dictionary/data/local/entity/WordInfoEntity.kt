package com.roshanadke.dictionaryapp.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.roshanadke.dictionaryapp.feature_dictionary.domain.models.Meaning
import com.roshanadke.dictionaryapp.feature_dictionary.domain.models.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val sourceUrls: List<String>,
    val word: String,
    @PrimaryKey val id: Int? = null,
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            phonetic = phonetic,
            sourceUrls = sourceUrls,
            word = word,
        )
    }
}