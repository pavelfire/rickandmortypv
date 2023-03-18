package com.vk.directop.rickandmortypv.presentation

import androidx.lifecycle.*
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.usecases.GetEpisodesUseCase
import kotlinx.coroutines.launch

class EpisodesViewModel(
    private val getEpisodesUseCase: GetEpisodesUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _episodes = MutableLiveData<List<EpisodeDTO>>()
    val episodes: LiveData<List<EpisodeDTO>> = _episodes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteEpisodes = arrayListOf<EpisodeDTO>()

    fun getEpisodes() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val episodesResult = getEpisodesUseCase.invoke()) {
                is Resultss.Success -> {
                    _remoteEpisodes.clear()
                    _remoteEpisodes.addAll(episodesResult.data)

                    _episodes.value = _remoteEpisodes
                    _dataLoading.postValue(false)
                }
                is Resultss.Error -> {
                    _dataLoading.postValue(false)
                    _episodes.value = emptyList()
                    _error.postValue(episodesResult.exception.message)
                    _dataLoading.postValue(false)
                }
            }
        }
    }


    class EpisodesViewModelFactory(
        private val getEpisodesUseCase: GetEpisodesUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesViewModel(
                getEpisodesUseCase
            ) as T
        }
    }
}