package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {
    suspend fun characterDb(character: CharacterDTO)
    suspend fun characterUn(character: CharacterDTO)
    suspend fun getDbCharacters(): Flow<List<CharacterDTO>>
}