package com.vk.directop.rickandmortypv.data.repositories.episodes

import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDto
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.repositories.EpisodesRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    val remoteDataSource: EpisodesRemoteDataSource,
        ): EpisodesRepository {

    override suspend fun getRemoteEpisodes(name: String): Resultss<List<EpisodeDto>> {
        return remoteDataSource.getEpisodes(name)
    }
}