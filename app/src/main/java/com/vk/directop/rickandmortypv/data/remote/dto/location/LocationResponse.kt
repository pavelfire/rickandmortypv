package com.vk.directop.rickandmortypv.data.remote.dto.location

data class LocationResponse(
    val info: Info,
    val results: List<LocationDTO>
)