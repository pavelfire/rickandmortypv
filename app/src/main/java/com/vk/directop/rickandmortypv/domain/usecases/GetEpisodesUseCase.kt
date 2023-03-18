package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.domain.repositories.EpisodesRepository

class GetEpisodesUseCase(private val episodesRepository: EpisodesRepository) {
    suspend operator fun invoke() = episodesRepository.getRemoteEpisodes()
}