package com.vk.directop.rickandmortypv.domain.repositories

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersParams
import com.vk.directop.rickandmortypv.domain.common.Resultss
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getRemoteCharacters(charactersParams: CharactersParams): Resultss<List<CharacterDTO>>

    suspend fun getSavedCharacters(charactersParams: CharactersParams): Flow<List<CharacterDTO>>

    suspend fun characterSaveToDb(character: Array<CharacterDTO>)

    suspend fun characterDeleteFromDb(character: CharacterDTO)


}