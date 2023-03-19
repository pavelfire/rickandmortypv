package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.domain.repositories.LocationsRepository

class GetLocationsRxUseCase(private val locationsRepository: LocationsRepository) {
    operator fun invoke(name: String) = locationsRepository.getRemoteLocationsRx(name)
}