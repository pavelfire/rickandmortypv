package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(val locationsRepository: LocationsRepository) {
    suspend operator fun invoke(name: String) = locationsRepository.getRemoteLocations(name)
}