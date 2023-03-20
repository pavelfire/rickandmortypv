package com.vk.directop.rickandmortypv.data.repositories.episodes

import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss

interface EpisodesRemoteDataSource {

    suspend fun getEpisodes(name: String): Resultss<List<EpisodeDTO>>
}