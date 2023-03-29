package com.vk.directop.rickandmortypv.data.repositories.locations

import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import java.lang.Exception

class LocationsParams(
    val name: String = "",
    val type: String = "",
    val dimension: String = "",
    val page: Int = 0,
)

sealed class LocatResult {
    data class Success(val data: List<LocationDTO>) : LocatResult()
    data class Error(val error: Exception) : LocatResult()
}