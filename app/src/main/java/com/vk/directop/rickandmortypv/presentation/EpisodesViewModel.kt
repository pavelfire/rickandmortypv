package com.vk.directop.rickandmortypv.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDTO

class EpisodesViewModel(

) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _episodes = MutableLiveData<List<EpisodeDTO>>()
    val episodes: LiveData<List<EpisodeDTO>> = _episodes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    class EpisodesViewModelFactory(

    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesViewModel(

            ) as T
        }
    }
}