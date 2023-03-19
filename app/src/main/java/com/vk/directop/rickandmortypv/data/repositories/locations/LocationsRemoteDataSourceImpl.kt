package com.vk.directop.rickandmortypv.data.repositories.locations

import com.vk.directop.rickandmortypv.data.mappers.ApiResponseMapper
import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationsRemoteDataSourceImpl(
    private val service: RickAndMortyApi,
    private val mapper: ApiResponseMapper,
) : LocationsRemoteDataSource {

    override suspend fun getLocations(name: String): Resultss<List<LocationDTO>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getLocations(name = name)
                if (response.isSuccessful) {
                    return@withContext Resultss.Success(mapper.toLocationsList(response.body()!!))
                } else {
                    return@withContext Resultss.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Resultss.Error(e)
            }
        }
}