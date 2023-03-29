package com.vk.directop.rickandmortypv.domain.repositories

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss

interface CharactersRepository {

    suspend fun getRemoteCharacters(name: String): Resultss<List<CharacterDTO>>

    //suspend fun getSavedCharacters(): Flow<List<CharacterDTO>>


}