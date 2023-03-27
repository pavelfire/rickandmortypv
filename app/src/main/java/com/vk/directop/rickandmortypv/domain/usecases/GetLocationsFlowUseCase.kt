package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryRM

class GetLocationsFlowUseCase(private val locationsRepository: LocationsRepositoryRM) {
    operator fun invoke(name: String) = locationsRepository.getSearchResultStream(name)
}