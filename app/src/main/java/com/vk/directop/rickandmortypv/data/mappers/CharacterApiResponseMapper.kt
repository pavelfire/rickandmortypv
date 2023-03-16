package com.vk.directop.rickandmortypv.data.mappers

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterResponse

class CharacterApiResponseMapper {
    fun toMyList(response: CharacterResponse): List<CharacterDTO>{
        return response.results
    }
}