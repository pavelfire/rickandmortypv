package com.vk.directop.rickandmortypv.data.remote

import com.vk.directop.rickandmortypv.data.remote.data_transfer_object.character.CharacterResponse
import com.vk.directop.rickandmortypv.data.remote.data_transfer_object.episode.EpisodeResponse
import com.vk.directop.rickandmortypv.data.remote.data_transfer_object.location.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(
        @Query("page")
        pageNumber: Int = 1
    ): Response<CharacterResponse>

    @GET("/api/episode")
    suspend fun getEpisodes(): Response<EpisodeResponse>

    @GET("/api/location")
    suspend fun getLocations(): Response<LocationResponse>


}