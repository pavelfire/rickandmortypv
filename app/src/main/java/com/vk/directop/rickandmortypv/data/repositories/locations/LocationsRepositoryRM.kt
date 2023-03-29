package com.vk.directop.rickandmortypv.data.repositories.locations

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vk.directop.rickandmortypv.data.db.LocationsDatabase
import com.vk.directop.rickandmortypv.data.entities.LocationEntity
import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationsRepositoryRM @Inject constructor(
     val service: RickAndMortyApi,
     val database: LocationsDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getSearchResultStream(query: String): Flow<PagingData<LocationEntity>> {
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { database.locationDao().locationsByName(dbQuery) }
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = LocationsRemoteMediator(
                query,
                service,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
