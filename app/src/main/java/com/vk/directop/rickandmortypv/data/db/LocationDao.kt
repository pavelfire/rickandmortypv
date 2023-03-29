package com.vk.directop.rickandmortypv.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vk.directop.rickandmortypv.data.entities.LOCATIONS_TABLE_NAME
import com.vk.directop.rickandmortypv.data.entities.LocationEntity

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<LocationEntity>)

    @Query(
        "SELECT * FROM $LOCATIONS_TABLE_NAME WHERE " +
                "name LIKE :queryString " +
                "ORDER BY name ASC"
    )
    fun locationsByName(queryString: String): PagingSource<Int, LocationEntity>

    @Query("DELETE FROM locations")
    suspend fun clearLocations()
}