package com.vk.directop.rickandmortypv.domain.repositories

import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss

interface LocationsRepository {

    suspend fun getRemoteLocations(name: String): Resultss<List<LocationDTO>>

}