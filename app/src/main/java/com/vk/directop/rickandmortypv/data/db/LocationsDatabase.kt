package com.vk.directop.rickandmortypv.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vk.directop.rickandmortypv.data.entities.LocationEntity

@Database(
    entities = [LocationEntity::class, LocationsRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class LocationsDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao
    abstract fun locationsRemoteKeysDao(): LocationsRemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: LocationsDatabase? = null

        fun getInstance(context: Context): LocationsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                LocationsDatabase::class.java, "Locations.db")
                .build()
    }
}