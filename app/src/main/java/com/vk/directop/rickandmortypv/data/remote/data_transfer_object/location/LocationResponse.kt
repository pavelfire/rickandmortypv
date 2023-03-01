package com.vk.directop.rickandmortypv.data.remote.data_transfer_object.location

data class LocationResponse(
    val info: Info,
    val results: List<LocationRM>
)