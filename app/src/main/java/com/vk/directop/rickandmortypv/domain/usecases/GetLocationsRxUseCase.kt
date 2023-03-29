package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetLocationsRxUseCase @Inject constructor(private val locationsRepository: LocationsRepository) {
    operator fun invoke(name: String) = locationsRepository.getRemoteLocationsRx(name)
}