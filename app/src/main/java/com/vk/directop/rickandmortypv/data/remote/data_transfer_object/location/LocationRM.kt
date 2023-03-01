package com.vk.directop.rickandmortypv.data.remote.data_transfer_object.location

data class LocationRM(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)