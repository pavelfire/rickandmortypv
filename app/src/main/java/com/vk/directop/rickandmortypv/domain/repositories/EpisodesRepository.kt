package com.vk.directop.rickandmortypv.domain.repositories

import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss

interface EpisodesRepository {

    suspend fun getRemoteEpisodes(): Resultss<List<EpisodeDTO>>

}