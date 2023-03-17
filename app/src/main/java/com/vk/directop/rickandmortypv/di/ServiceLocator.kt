package com.vk.directop.rickandmortypv.di

import android.content.Context
import com.vk.directop.rickandmortypv.data.api.NetworkModule
import com.vk.directop.rickandmortypv.data.db.CharactersDatabase
import com.vk.directop.rickandmortypv.data.mappers.CharacterApiResponseMapper
import com.vk.directop.rickandmortypv.data.mappers.CharacterEntityMapper
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersLocalDataSource
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersLocalDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRemoteDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRepositoryImpl
import kotlinx.coroutines.Dispatchers

const val BASE_URL = "https://rickandmortyapi.com"

object ServiceLocator {
    private var database: CharactersDatabase? = null
    private val networkModule by lazy {
        NetworkModule()
    }
    private val characterEntityMapper by lazy {
        CharacterEntityMapper()
    }

    @Volatile
    var charactersRepository: CharactersRepositoryImpl? = null

    fun provideCharactersRepository(context: Context): CharactersRepositoryImpl {
        synchronized(this) {
            return charactersRepository ?: createCharactersRepository(context)
        }
    }

    private fun createCharactersRepository(context: Context): CharactersRepositoryImpl {
        val newRepo =
            CharactersRepositoryImpl(
                //createCharactersLocalDataSource(context),
                CharactersRemoteDataSourceImpl(
                    networkModule.createCharactersApi(BASE_URL),
                    CharacterApiResponseMapper()
                )
            )
        charactersRepository = newRepo
        return newRepo
    }

    private fun createCharactersLocalDataSource(context: Context): CharactersLocalDataSource {
        val database = database ?: createDataBase(context)
        return CharactersLocalDataSourceImpl(
            database.characterDao(),
            Dispatchers.IO,
            characterEntityMapper
        )
    }

    private fun createDataBase(context: Context): CharactersDatabase {
        val result = CharactersDatabase.getDatabase(context)
        database = result
        return result
    }
}