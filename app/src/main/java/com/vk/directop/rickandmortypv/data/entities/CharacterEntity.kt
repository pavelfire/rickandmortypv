package com.vk.directop.rickandmortypv.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vk.directop.rickandmortypv.data.remote.dto.character.Location
import com.vk.directop.rickandmortypv.data.remote.dto.character.Origin

@Entity(tableName = "tablecharacters")
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