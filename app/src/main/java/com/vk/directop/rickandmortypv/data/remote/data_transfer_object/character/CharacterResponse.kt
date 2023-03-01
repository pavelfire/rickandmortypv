package com.vk.directop.rickandmortypv.data.remote.data_transfer_object.character

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterRM>
)