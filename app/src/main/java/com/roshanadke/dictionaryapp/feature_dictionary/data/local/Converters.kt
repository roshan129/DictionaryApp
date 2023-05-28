package com.roshanadke.dictionaryapp.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.roshanadke.dictionaryapp.feature_dictionary.data.util.JsonParser
import com.roshanadke.dictionaryapp.feature_dictionary.domain.models.Meaning

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json = json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        )?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromString(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json = json,
            object : TypeToken<ArrayList<String>>(){}.type
        )?: emptyList()
    }

    @TypeConverter
    fun toString(meanings: List<String>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: "[]"
    }

}