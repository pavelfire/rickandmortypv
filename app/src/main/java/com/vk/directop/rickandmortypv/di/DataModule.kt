package com.vk.directop.rickandmortypv.di

import com.vk.directop.rickandmortypv.data.mappers.ApiResponseMapper
import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRemoteDataSource
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRemoteDataSourceImpl
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRepositoryImpl
import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    private fun provideApiResponseMapper(): ApiResponseMapper{
        return ApiResponseMapper()
    }

    private fun provideRetrofitModule(): RickAndMortyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RickAndMortyApi::class.java)
    }

    fun provideRetrofitModuleRx(): RickAndMortyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(RickAndMortyApi::class.java)
    }

    private fun provideCharactersRemoteDataSource(): CharactersRemoteDataSource {
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
}