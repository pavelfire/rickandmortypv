package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss

interface CharactersRemoteDataSource {

    suspend fun getCharacters(name: String): Resultss<List<CharacterDTO>>
}