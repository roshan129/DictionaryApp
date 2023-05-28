package com.roshanadke.dictionaryapp.feature_dictionary.data.remote.dto

import com.roshanadke.dictionaryapp.feature_dictionary.domain.models.Meaning

data class MeaningDto(
    val antonyms: List<String>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
) {
    fun toMeaning(): Meaning {
        return Meaning(
            antonyms = antonyms,
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech,
            synonyms = synonyms,
        )
    }
}