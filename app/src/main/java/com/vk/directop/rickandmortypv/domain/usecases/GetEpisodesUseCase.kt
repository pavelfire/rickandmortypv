package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(val episodesRepository: EpisodesRepository) {
    suspend operator fun invoke(name: String) = episodesRepository.getRemoteEpisodes(name)
}