package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterResponse
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository

class CharactersRepositoryImpl (
    private val localDataSource: CharactersLocalDataSource,
    private val remoteDataSource: CharactersRemoteDataSource,
        ): CharactersRepository{

        override suspend fun getRemoteCharacters(): Resultss<List<CharacterDTO>> {
                return remoteDataSource.getCharacters()
        }

}