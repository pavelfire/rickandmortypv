package com.vk.directop.rickandmortypv.presentation

import androidx.lifecycle.*
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.usecases.GetEpisodesUseCase
import com.vk.directop.rickandmortypv.domain.usecases.GetLocationsUseCase
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _locations = MutableLiveData<List<LocationDTO>>()
    val locations: LiveData<List<LocationDTO>> = _locations

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteEpisodes = arrayListOf<LocationDTO>()

    fun getLocations() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val locationsResult = getLocationsUseCase.invoke()) {
                is Resultss.Success -> {
                    _remoteEpisodes.clear()
                    _remoteEpisodes.addAll(locationsResult.data)

                    _locations.value = _remoteEpisodes
                    _dataLoading.postValue(false)
                }
                is Resultss.Error -> {
                    _dataLoading.postValue(false)
                    _locations.value = emptyList()
                    _error.postValue(locationsResult.exception.message)
                    _dataLoading.postValue(false)
                }
            }
        }
    }


    class LocationsViewModelFactory(
        private val getLocationsUseCase: GetLocationsUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LocationsViewModel(
                getLocationsUseCase
            ) as T
        }
    }
}