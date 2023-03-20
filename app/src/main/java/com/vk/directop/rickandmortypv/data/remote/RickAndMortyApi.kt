package com.vk.directop.rickandmortypv.data.remote

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterResponse
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeResponse
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(
        @Query("page")
        pageNumber: Int = 1,
        @Query("name")
        name: String = "",
        @Query("gender")
        gender: String = "",
        @Query("status")
        status: String = "",
    ): Response<CharacterResponse>

    @GET("/api/episode")
    suspend fun getEpisodes(
        @Query("page")
        pageNumber: Int = 1,
        @Query("name")
        name: String = "",
        @Query("episode")
        episode: String = "",
    ): Response<EpisodeResponse>

    @GET("/api/location")
    suspend fun getLocations(
        @Query("name")
        name: String = "",
        @Query("type")
        type: String = "",
        @Query("dimension")
        dimension: String = "",
    ): Response<LocationResponse>

    @GET("/api/location")
    fun getLocationsRx(
        @Query("name")
        name: String = "",
        @Query("type")
        type: String = "",
        @Query("dimension")
        dimension: String = "",
    ): Single<Response<LocationResponse>>


}