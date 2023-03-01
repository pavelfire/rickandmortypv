package com.vk.directop.rickandmortypv.data.remote.data_transfer_object.episode

data class EpisodeRM(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)