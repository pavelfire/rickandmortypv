package com.vk.directop.rickandmortypv.data.repositories.episodes

import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDto
import com.vk.directop.rickandmortypv.domain.common.Resultss

interface EpisodesRemoteDataSource {

    suspend fun getEpisodes(name: String): Resultss<List<EpisodeDto>>
}