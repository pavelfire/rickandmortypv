package com.vk.directop.rickandmortypv.data.repositories.locations

import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationResponse
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.repositories.LocationsRepository
import io.reactivex.Single
import retrofit2.Response

class LocationsRepositoryImpl (
    private val remoteDataSource: LocationsRemoteDataSource,
        ): LocationsRepository {

    override suspend fun getRemoteLocations(name: String): Resultss<List<LocationDTO>> {
        return remoteDataSource.getLocations(name)
    }

    override fun getRemoteLocationsRx(name: String): Single<Response<LocationResponse>> {
        return remoteDataSource.getLocationsRx(name = name)
    }
}