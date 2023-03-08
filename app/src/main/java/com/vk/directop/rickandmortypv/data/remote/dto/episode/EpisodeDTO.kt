package com.vk.directop.rickandmortypv.data.remote.dto.episode

data class EpisodeDTO(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)