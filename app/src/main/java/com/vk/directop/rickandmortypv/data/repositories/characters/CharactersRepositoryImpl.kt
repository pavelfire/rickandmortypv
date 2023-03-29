package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl (
   private val localDataSource: CharactersLocalDataSource,
    private val remoteDataSource: CharactersRemoteDataSource,
        ): CharactersRepository{

    override suspend fun getRemoteCharacters(name: String): Resultss<List<CharacterDTO>> {
        return remoteDataSource.getCharacters(name)
    }

    override suspend fun getSavedCharacters(name: String): Flow<List<CharacterDTO>> {
        return localDataSource.getDbCharacters(name)
    }

    override suspend fun characterSaveToDb(character: Array<CharacterDTO>) {
        return localDataSource.characterSaveToDb(character)
    }

    override suspend fun characterDeleteFromDb(character: CharacterDTO) {
        return localDataSource.characterDeleteFromDb(character)
    }

}