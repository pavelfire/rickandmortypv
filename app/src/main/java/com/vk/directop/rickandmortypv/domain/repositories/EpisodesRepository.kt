package com.vk.directop.rickandmortypv.domain.repositories

import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDto
import com.vk.directop.rickandmortypv.domain.common.Resultss

interface EpisodesRepository {

    suspend fun getRemoteEpisodes(name: String): Resultss<List<EpisodeDto>>

}