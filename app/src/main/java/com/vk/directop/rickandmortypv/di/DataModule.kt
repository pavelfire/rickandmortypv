package com.vk.directop.rickandmortypv.di

import android.content.Context
import com.vk.directop.rickandmortypv.data.db.LocationsDatabase
import com.vk.directop.rickandmortypv.data.mappers.ApiResponseMapper
import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRemoteDataSource
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRemoteDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.episodes.EpisodesRemoteDataSource
import com.vk.directop.rickandmortypv.data.repositories.episodes.EpisodesRemoteDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.episodes.EpisodesRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRemoteDataSource
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRemoteDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryImpl
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryRM
import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import com.vk.directop.rickandmortypv.domain.repositories.EpisodesRepository
import com.vk.directop.rickandmortypv.domain.repositories.LocationsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://rickandmortyapi.com"

@Module
class DataModule {

    @Provides
    fun provideApiResponseMapper(): ApiResponseMapper {
        return ApiResponseMapper()
    }

    @Provides
    fun provideRetrofitModule(): RickAndMortyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RickAndMortyApi::class.java)
    }

    @Provides
    fun provideRetrofitModuleRx(): RickAndMortyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(RickAndMortyApi::class.java)
    }

    @Provides
    fun provideCharactersRemoteDataSource(): CharactersRemoteDataSource {
        return CharactersRemoteDataSourceImpl(
            provideRetrofitModule(),
            provideApiResponseMapper()
        )
    }

    @Provides
    fun provideCharactersRepository(): CharactersRepository {
        return CharactersRepositoryImpl(
            provideCharactersRemoteDataSource()
        )
    }

    @Provides
    fun provideLocationsRemoteDataSource(): LocationsRemoteDataSource {
        return LocationsRemoteDataSourceImpl(
            provideRetrofitModuleRx(),
            provideApiResponseMapper()
        )
    }

    @Provides
    fun provideLocationsRepository(): LocationsRepository {
        return LocationsRepositoryImpl(
            provideLocationsRemoteDataSource()
        )
    }

    @Provides
    fun provideLocationsRepositoryRM(context: Context): LocationsRepositoryRM {
        return LocationsRepositoryRM(
            provideRetrofitModuleRx(),
            provideLocationsDatabase(context),
        )
    }

    @Provides
    fun provideLocationsDatabase(context: Context): LocationsDatabase {
        return LocationsDatabase.getInstance(context)

    }

    @Provides
    fun provideEpisodesRemoteDataSource(): EpisodesRemoteDataSource {
        return EpisodesRemoteDataSourceImpl(
            provideRetrofitModule(),
            provideApiResponseMapper()
        )
    }

    @Provides
    fun provideEpisodesRepository(): EpisodesRepository {
        return EpisodesRepositoryImpl(
            provideEpisodesRemoteDataSource()
        )
    }
}