package com.vk.directop.rickandmortypv.data.repositories.locations

import android.util.Log
import com.vk.directop.rickandmortypv.data.mappers.ApiResponseMapper
import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationResponse
import com.vk.directop.rickandmortypv.domain.common.Resultss
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class LocationsRemoteDataSourceImpl @Inject constructor(
    private val service: RickAndMortyApi,
    private val mapper: ApiResponseMapper,
) : LocationsRemoteDataSource {

    private val inMemoryCache = mutableListOf<LocationDTO>()
    private val searchResults = MutableSharedFlow<LocatResult>(replay = 1)
    private var lastRequestedPage = STARTING_PAGE_INDEX
    private var isRequestInProgress = false


    override suspend fun getLocationsP(query: LocationsParams): Flow<LocatResult> {
        Log.d("TAG", "New query: $query")
        lastRequestedPage = STARTING_PAGE_INDEX
        inMemoryCache.clear()
        requestAndSaveData(query)

        return searchResults
    }

    suspend fun requestMore(query: LocationsParams) {
        if (isRequestInProgress) return
        val successful = requestAndSaveData(query)
        if (successful) {
            lastRequestedPage++
        }
    }

    suspend fun retry(query: LocationsParams) {
        if (isRequestInProgress) return
        requestAndSaveData(query)
    }

    private suspend fun requestAndSaveData(params: LocationsParams): Boolean {
        isRequestInProgress = true
        var successful = false

        try {
            val response = service.getLocations(
                name = params.name,
                type = params.type,
                dimension = params.dimension,
                page = lastRequestedPage,
            )
            Log.d("LocationsRepository", "---------response $response")
            Log.d("LocationsRepository", "=========lastRequestedPage: $lastRequestedPage")

            val repos = response.body()?.results ?: emptyList()
            inMemoryCache.addAll(repos)
            val reposByName = reposByName(params.name)
            searchResults.emit(LocatResult.Success(reposByName))
            successful = true
        } catch (exception: IOException) {
            searchResults.emit(LocatResult.Error(exception))
        } catch (exception: HttpException) {
            searchResults.emit(LocatResult.Error(exception))
        }
        isRequestInProgress = false
        return successful
    }

    private fun reposByName(query: String): List<LocationDTO> {
        return inMemoryCache.filter {
            it.name.contains(query, true) ||
                    (it.name != null && it.name.contains(query, true))
        }
        //.sortedWith(compareByDescending<LocationDTO> { it.id }.thenBy { it.name })
    }

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

    override fun getLocationsRx(name: String): Single<Response<LocationResponse>> {
        return service.getLocationsRx(name = name)
            .subscribeOn(Schedulers.io())
    }
}