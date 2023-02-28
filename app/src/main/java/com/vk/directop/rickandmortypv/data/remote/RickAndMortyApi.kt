package com.vk.directop.rickandmortypv.data.remote

import com.vk.directop.rickandmortypv.data.remote.dto.CharacterRM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(): Response<List<CharacterRM>>

//    @GET("/api/character")
//    fun getCharacters1(@Query("key") key: String): Response<List<CharacterRM>>

}