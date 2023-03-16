package com.vk.directop.rickandmortypv.domain.repositories

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss

interface CharactersRepository {

    suspend fun getRemoteCharacters(): Resultss<List<CharacterDTO>>


}