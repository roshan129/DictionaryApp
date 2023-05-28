package com.roshanadke.dictionaryapp.feature_dictionary.domain.models


data class Meaning(
    val antonyms: List<String>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)
