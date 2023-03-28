package com.vk.directop.rickandmortypv.di

import android.content.Context
import com.vk.directop.rickandmortypv.data.api.NetworkModule
import com.vk.directop.rickandmortypv.data.db.CharactersDatabase
import com.vk.directop.rickandmortypv.data.db.LocationsDatabase
import com.vk.directop.rickandmortypv.data.mappers.ApiResponseMapper
import com.vk.directop.rickandmortypv.data.mappers.CharacterEntityMapper
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersLocalDataSource
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersLocalDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRemoteDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.episodes.EpisodesRemoteDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.episodes.EpisodesRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRemoteDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryRM
import kotlinx.coroutines.Dispatchers


object ServiceLocator {
//    private var database: CharactersDatabase? = null
    private val networkModule by lazy {
        NetworkModule()
    }
//    private val characterEntityMapper by lazy {
//        CharacterEntityMapper()
//    }
//
//    @Volatile
//    var charactersRepository: CharactersRepositoryImpl? = null
//
//    fun provideCharactersRepository(context: Context): CharactersRepositoryImpl {
//        synchronized(this) {
//            return charactersRepository ?: createCharactersRepository(context)
//        }
//    }
//
//    private fun createCharactersRepository(context: Context): CharactersRepositoryImpl {
//        val newRepo =
//            CharactersRepositoryImpl(
//                //createCharactersLocalDataSource(context),
//                CharactersRemoteDataSourceImpl(
//                    networkModule.createCharactersApi(BASE_URL),
//                    ApiResponseMapper()
//                )
//            )
//        charactersRepository = newRepo
//        return newRepo
//    }
//
//    private fun createCharactersLocalDataSource(context: Context): CharactersLocalDataSource {
//        val database = database ?: createDataBase(context)
//        return CharactersLocalDataSourceImpl(
//            database.characterDao(),
//            Dispatchers.IO,
//            characterEntityMapper
//        )
//    }
//
//    private fun createDataBase(context: Context): CharactersDatabase {
//        val result = CharactersDatabase.getDatabase(context)
//        database = result
//        return result
//    }
//
//    @Volatile
//    var episodesRepository: EpisodesRepositoryImpl? = null
//
//    fun provideEpisodesRepository(context: Context): EpisodesRepositoryImpl{
//        synchronized(this){
//            return episodesRepository ?: createEpisodesRepository(context)
//        }
//    }
//
//    private fun createEpisodesRepository(context: Context): EpisodesRepositoryImpl {
//        val newRepo =
//            EpisodesRepositoryImpl(
//                EpisodesRemoteDataSourceImpl(
//                    networkModule.createCharactersApi(BASE_URL),
//                    ApiResponseMapper()
//                )
//            )
//        episodesRepository = newRepo
//        return newRepo
//    }
//
//    @Volatile
//    var locationsRepository: LocationsRepositoryImpl? = null
//
//    fun provideLocationsRepository(context: Context): LocationsRepositoryImpl{
//        synchronized(this){
//            return locationsRepository ?: createLocationsRepository(context)
//        }
//    }
//
//    private fun createLocationsRepository(context: Context): LocationsRepositoryImpl {
//        val newRepo =
//            LocationsRepositoryImpl(
//                LocationsRemoteDataSourceImpl(
//                    networkModule.createCharactersApi(BASE_URL),
//                    ApiResponseMapper()
//                )
//            )
//        locationsRepository = newRepo
//        return newRepo
//    }
//
//    // ---RX----
//    @Volatile
//    var locationsRepositoryRx: LocationsRepositoryImpl? = null
//
//    fun provideLocationsRepositoryRx(context: Context): LocationsRepositoryImpl{
//        synchronized(this){
//            return locationsRepositoryRx ?: createLocationsRepositoryRx(context)
//        }
//    }
//
//    private fun createLocationsRepositoryRx(context: Context): LocationsRepositoryImpl {
//        val newRepo =
//            LocationsRepositoryImpl(
//                LocationsRemoteDataSourceImpl(
//                    networkModule.createRxApi(BASE_URL),
//                    ApiResponseMapper()
//                )
//            )
//        locationsRepositoryRx = newRepo
//        return newRepo
//    }
//
//    //--------------------
//    @Volatile
//    var locationsRepositoryRM: LocationsRepositoryRM? = null
//
//    fun provideLocationsRepositoryRM(context: Context): LocationsRepositoryRM{
//        synchronized(this){
//            return locationsRepositoryRM ?: createLocationsRepositoryRM(context)
//        }
//    }
//
//    private fun createLocationsRepositoryRM(context: Context): LocationsRepositoryRM {
//        val newRepo =
//            LocationsRepositoryRM(
//
//                    networkModule.createCharactersApi(BASE_URL),
//                    createLocDatabase(context)
//
//            )
//        locationsRepositoryRM = newRepo
//        return newRepo
//    }
//
//    private var locDatabase: LocationsDatabase? = null
//
//    private fun createLocDatabase(context: Context): LocationsDatabase{
//        val result = LocationsDatabase.getInstance(context)
//        locDatabase = result
//        return result
//    }
}