package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository

class CharactersRepositoryImpl (
   //private val localDataSource: CharactersLocalDataSource,
    private val remoteDataSource: CharactersRemoteDataSource,
        ): CharactersRepository{


    override suspend fun getRemoteCharacters(name: String): Resultss<List<CharacterDTO>> {
        return remoteDataSource.getCharacters(name)
    }

//    override suspend fun getSavedCharacters(): Flow<List<CharacterDTO>> {
//        return localDataSource.getDbCharacters()
//    }

}