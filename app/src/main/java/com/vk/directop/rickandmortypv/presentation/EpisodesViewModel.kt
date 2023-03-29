package com.vk.directop.rickandmortypv.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.vk.directop.rickandmortypv.data.remote.dto.episode.EpisodeDto
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.usecases.GetEpisodesUseCase
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EpisodesViewModel(
    private val getEpisodesUseCase: GetEpisodesUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _episodes = MutableLiveData<List<EpisodeDto>>()
    val episodes: LiveData<List<EpisodeDto>> = _episodes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteEpisodes = arrayListOf<EpisodeDto>()

    private val _searchFilter = MutableLiveData<String>("")
    val searchFilter: LiveData<String> = _searchFilter

    private val editTextSubject = PublishSubject.create<String>()

    @SuppressLint("CheckResult")
    fun searchName(text: String, sendButton: Boolean) {

        _searchFilter.value = text

        if (sendButton) getEpisodes(_searchFilter.value.toString())
        else {
            editTextSubject.onNext(text)
            editTextSubject
                .debounce(2000, TimeUnit.MILLISECONDS)
                .subscribe {
                    getEpisodes(_searchFilter.value.toString())
                }
        }
    }

    fun getEpisodes(name: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val episodesResult = getEpisodesUseCase.invoke(name = name)) {
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


    class EpisodesViewModelFactory @Inject constructor(
        val getEpisodesUseCase: GetEpisodesUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesViewModel(
                getEpisodesUseCase
            ) as T
        }
    }
}