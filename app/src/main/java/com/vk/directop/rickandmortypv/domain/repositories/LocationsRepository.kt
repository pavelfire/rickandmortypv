package com.vk.directop.rickandmortypv.domain.repositories

import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationResponse
import com.vk.directop.rickandmortypv.domain.common.Resultss
import io.reactivex.Single
import retrofit2.Response

interface LocationsRepository {

    suspend fun getRemoteLocations(name: String): Resultss<List<LocationDTO>>

    fun getRemoteLocationsRx(name: String): Single<Response<LocationResponse>>

}