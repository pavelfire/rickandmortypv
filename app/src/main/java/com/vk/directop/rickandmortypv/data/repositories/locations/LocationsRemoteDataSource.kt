package com.vk.directop.rickandmortypv.data.repositories.locations

import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationResponse
import com.vk.directop.rickandmortypv.domain.common.Resultss
import io.reactivex.Single
import retrofit2.Response

interface LocationsRemoteDataSource {

    suspend fun getLocations(name: String): Resultss<List<LocationDTO>>

    fun getLocationsRx(name: String): Single<Response<LocationResponse>>
}