package com.vk.directop.rickandmortypv.data.repositories.locations

import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.repositories.LocationsRepository

class LocationsRepositoryImpl (
    private val remoteDataSource: LocationsRemoteDataSource,
        ): LocationsRepository {

    override suspend fun getRemoteLocations(): Resultss<List<LocationDTO>> {
        return remoteDataSource.getLocations()
    }
}