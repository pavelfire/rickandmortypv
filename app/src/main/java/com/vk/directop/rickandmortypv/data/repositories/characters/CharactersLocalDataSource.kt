package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {

    suspend fun characterSaveToDb(character: Array<CharacterDTO>)

    suspend fun characterDeleteFromDb(character: CharacterDTO)

    suspend fun getDbCharacters(name: String): Flow<List<CharacterDTO>>

}