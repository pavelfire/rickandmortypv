package com.vk.directop.rickandmortypv.data.repositories.locations

import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationResponse
import com.vk.directop.rickandmortypv.domain.common.Resultss
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface LocationsRemoteDataSource {

    suspend fun getLocations(name: String): Resultss<List<LocationDTO>>

    suspend fun getLocationsP(query: LocationsParams): Flow<LocatResult>

    fun getLocationsRx(name: String): Single<Response<LocationResponse>>
}