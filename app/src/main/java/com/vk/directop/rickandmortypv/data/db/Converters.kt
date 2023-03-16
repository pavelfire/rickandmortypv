package com.vk.directop.rickandmortypv.data.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromEpisodesList(value: List<String>): String {
        return value.joinToString { "," }
    }

    @TypeConverter
    fun toEpisodesList(value: String): List<String> {
        return value.split(",")
    }
}