package com.roshanadke.dictionaryapp.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.roshanadke.dictionaryapp.feature_dictionary.data.local.Converters
import com.roshanadke.dictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.roshanadke.dictionaryapp.feature_dictionary.data.local.WordInfoDatabase
import com.roshanadke.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.roshanadke.dictionaryapp.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.roshanadke.dictionaryapp.feature_dictionary.data.util.GsonParser
import com.roshanadke.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import com.roshanadke.dictionaryapp.feature_dictionary.domain.use_case.GetWordInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WordInfoModule {


    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfoUseCase {
        return GetWordInfoUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDb(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java, "word_info_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(DictionaryApi::class.java)
    }

}