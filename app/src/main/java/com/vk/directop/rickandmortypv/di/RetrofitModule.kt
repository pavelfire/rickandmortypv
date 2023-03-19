package com.vk.directop.rickandmortypv.di

import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//@Module
object RetrofitModule {

    //@Provides
    fun provideRepository(){

    }

    //@Provides
    fun provideNetworkService(): RickAndMortyApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(RickAndMortyApi::class.java)
    }


}