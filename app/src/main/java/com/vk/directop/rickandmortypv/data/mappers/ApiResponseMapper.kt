package com.vk.directop.rickandmortypv.data.mappers

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterResponse
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDto
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeResponse
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationResponse

class ApiResponseMapper {

    fun toMyList(response: CharacterResponse): List<CharacterDTO>{
        return response.results
    }

    fun toEpisodesList(response: EpisodeResponse): List<EpisodeDto>{
        return response.results
    }

    fun toLocationsList(response: LocationResponse): List<LocationDTO>{
        return response.results
    }
}