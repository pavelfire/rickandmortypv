package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(
    private val localDataSource: CharactersLocalDataSource,
    private val remoteDataSource: CharactersRemoteDataSource,
) : CharactersRepository {

    override suspend fun getRemoteCharacters(charactersParams: CharactersParams): Resultss<List<CharacterDTO>> {
        return remoteDataSource.getCharacters(charactersParams)
    }

    override suspend fun getSavedCharacters(charactersParams: CharactersParams): Flow<List<CharacterDTO>> {
        return localDataSource.getDbCharacters(charactersParams)
    }

    override suspend fun characterSaveToDb(character: Array<CharacterDTO>) {
        return localDataSource.characterSaveToDb(character)
    }

    override suspend fun characterDeleteFromDb(character: CharacterDTO) {
        return localDataSource.characterDeleteFromDb(character)
    }

}