package com.vk.directop.rickandmortypv.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.remote.dto.character.Location
import com.vk.directop.rickandmortypv.data.remote.dto.character.Origin

const val CHARACTERS_TABLE_NAME = "table_characters"

@Entity(tableName = CHARACTERS_TABLE_NAME)
data class CharacterEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun CharacterEntity.mapToCharacterDTO(el: CharacterEntity): CharacterDTO {
    return CharacterDTO(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location,
        name = name,
        origin = origin,
        species = species,
        status = status,
        type = type,
        url = url
    )
}