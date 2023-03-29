package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.domain.repositories.LocationsRepository

class GetLocationsUseCase(private val locationsRepository: LocationsRepository) {
    suspend operator fun invoke(name: String) = locationsRepository.getRemoteLocations(name)
}