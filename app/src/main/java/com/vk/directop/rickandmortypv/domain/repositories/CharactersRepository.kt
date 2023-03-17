package com.vk.directop.rickandmortypv.domain.repositories

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getRemoteCharacters(): Resultss<List<CharacterDTO>>

    //suspend fun getSavedCharacters(): Flow<List<CharacterDTO>>


}