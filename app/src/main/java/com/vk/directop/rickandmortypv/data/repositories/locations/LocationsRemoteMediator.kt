package com.vk.directop.rickandmortypv.data.repositories.locations

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vk.directop.rickandmortypv.data.db.LocationsDatabase
import com.vk.directop.rickandmortypv.data.db.LocationsRemoteKeys
import com.vk.directop.rickandmortypv.data.entities.LocationEntity
import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import com.vk.directop.rickandmortypv.data.remote.dto.location.mapToEntity
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class LocationsRemoteMediator(
    private val query: String,
    private val service: RickAndMortyApi,
    private val locationsDatabase: LocationsDatabase
) : RemoteMediator<Int, LocationEntity>() {

    private var pageIndex = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationEntity>
    ): MediatorResult {

        pageIndex =
            getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        val apiQuery = query

        try {
            val apiResponse = service.getLocations(
                name = apiQuery,
                type = "",
                dimension = "",
                page = pageIndex,
            )

            val locations = apiResponse.body()!!.results
            val endOfPaginationReached = apiResponse.body()!!.info.pages == pageIndex
            locationsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    locationsDatabase.locationsRemoteKeysDao().clearRemoteKeys()
                    locationsDatabase.locationDao().clearLocations()
                }
                val prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex - 1
                val nextKey = if (endOfPaginationReached) null else pageIndex + 1
                val keys = locations.map {
                    LocationsRemoteKeys(id = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                locationsDatabase.locationsRemoteKeysDao().insertAll(keys)
                locationsDatabase.locationDao().insertAll(locations.map { it.mapToEntity(it) })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }
}