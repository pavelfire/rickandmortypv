package com.vk.directop.rickandmortypv.data.remote.dto.location

data class LocationDTO(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)