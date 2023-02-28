package com.vk.directop.rickandmortypv.data.remote

import com.vk.directop.rickandmortypv.data.remote.data_transfer_object.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(): Response<CharacterResponse>

//    @GET("/api/character")
//    fun getCharacters1(@Query("key") key: String): Response<List<CharacterRM>>

}