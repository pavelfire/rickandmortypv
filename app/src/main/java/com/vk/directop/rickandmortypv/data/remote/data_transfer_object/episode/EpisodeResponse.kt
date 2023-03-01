package com.vk.directop.rickandmortypv.data.remote.data_transfer_object.episode

data class EpisodeResponse(
    val info: Info,
    val results: List<EpisodeRM>
)