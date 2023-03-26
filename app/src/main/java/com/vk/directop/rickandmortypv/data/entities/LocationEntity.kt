package com.vk.directop.rickandmortypv.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO

const val LOCATIONS_TABLE_NAME = "locations"

@Entity(tableName = LOCATIONS_TABLE_NAME)
data class LocationEntity(
    val created: String,
    val dimension: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val residents: String,
    val type: String,
    val url: String
)

fun LocationEntity.mapToDTO(): LocationDTO {
    return LocationDTO(
        created = created,
        dimension = dimension,
        id = id,
        name = name,
        residents = residents.lines(),
        type = type,
        url = url
    )
}