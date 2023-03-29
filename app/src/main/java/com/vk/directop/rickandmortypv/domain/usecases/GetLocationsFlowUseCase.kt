package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryRM
import javax.inject.Inject

class GetLocationsFlowUseCase @Inject constructor(val locationsRepository: LocationsRepositoryRM) {
    operator fun invoke(name: String) = locationsRepository.getSearchResultStream(name)
}