package com.vk.directop.rickandmortypv.data.repositories.episodes

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterResponse
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersRemoteDataSource
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import com.vk.directop.rickandmortypv.domain.repositories.EpisodesRepository
import kotlinx.coroutines.flow.Flow

class EpisodesRepositoryImpl (
    private val remoteDataSource: EpisodesRemoteDataSource,
        ): EpisodesRepository {

    override suspend fun getRemoteEpisodes(): Resultss<List<EpisodeDTO>> {
        return remoteDataSource.getEpisodes()
    }
}