package com.vk.directop.rickandmortypv.data.repositories.locations

import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss

interface LocationsRemoteDataSource {

    suspend fun getLocations(name: String): Resultss<List<LocationDTO>>
}