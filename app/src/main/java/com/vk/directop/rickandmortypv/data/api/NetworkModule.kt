package com.vk.directop.rickandmortypv.data.api

import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class NetworkModule {

    private val loggingInterceptor by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        loggingInterceptor
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun getRetrofit(endpointURL: String): Retrofit{
        return Retrofit.Builder()
            .baseUrl(endpointURL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createCharactersApi(endpointURL: String): RickAndMortyApi{
        val retrofit = getRetrofit(endpointURL)
        return retrofit.create(RickAndMortyApi::class.java)
    }

    fun createRxApi(endpointURL: String): RickAndMortyApi{
        val retrofit = getRetrofitRx(endpointURL)
        return retrofit.create(RickAndMortyApi::class.java)
    }

    private fun getRetrofitRx(endpointURL: String): Retrofit{
        return Retrofit.Builder()
            .baseUrl(endpointURL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}