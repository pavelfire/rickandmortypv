package com.vk.directop.rickandmortypv.data.remote.dto.location

import android.os.Parcelable
import com.vk.directop.rickandmortypv.data.entities.LocationEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationDTO(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
) : Parcelable

fun LocationDTO.mapToEntity(locationDTO: LocationDTO): LocationEntity {
    return LocationEntity(
        created = created,
        dimension = dimension,
        id = id,
        name = name,
        residents = residents.joinToString(separator = "\n"),
        type = type,
        url = url
    )
}