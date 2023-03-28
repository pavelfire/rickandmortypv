package com.vk.directop.rickandmortypv.data.repositories.episodes

import com.vk.directop.rickandmortypv.data.mappers.ApiResponseMapper
import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EpisodesRemoteDataSourceImpl @Inject constructor(
    val service: RickAndMortyApi,
    val mapper: ApiResponseMapper,
) : EpisodesRemoteDataSource {

    override suspend fun getEpisodes(name: String): Resultss<List<EpisodeDTO>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getEpisodes(name = name)
                if (response.isSuccessful) {
                    return@withContext Resultss.Success(mapper.toEpisodesList(response.body()!!))
                } else {
                    return@withContext Resultss.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Resultss.Error(e)
            }
        }


}