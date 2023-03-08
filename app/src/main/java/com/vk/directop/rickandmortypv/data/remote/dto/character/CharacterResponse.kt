package com.vk.directop.rickandmortypv.data.remote.dto.character

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterDTO>
)