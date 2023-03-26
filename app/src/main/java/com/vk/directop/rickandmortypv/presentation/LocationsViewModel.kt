package com.vk.directop.rickandmortypv.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.map
import com.vk.directop.rickandmortypv.data.entities.LocationEntity
import com.vk.directop.rickandmortypv.data.entities.mapToDTO
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.data.repositories.locations.LocationsRepositoryRM
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.usecases.GetLocationsRxUseCase
import com.vk.directop.rickandmortypv.domain.usecases.GetLocationsUseCase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private const val VISIBLE_THRESHOLD = -20
private const val LAST_SEARCH_QUERY: String = "last_search_query"
private const val DEFAULT_QUERY = ""

class LocationsViewModel(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getLocationsRxUseCase: GetLocationsRxUseCase,
    private val repository: LocationsRepositoryRM
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _locations = MutableLiveData<List<LocationDTO>>()
    val locations: LiveData<List<LocationDTO>> = _locations

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteLocations = arrayListOf<LocationDTO>()

    private val _searchFilter = MutableLiveData("")
    val searchFilter: LiveData<String> = _searchFilter

    private val editTextSubject = PublishSubject.create<String>()

    @SuppressLint("CheckResult")
    fun searchName(text: String, sendButton: Boolean) {

        _searchFilter.value = text

        if (sendButton) getLocations(_searchFilter.value.toString())
        else {
            editTextSubject.onNext(text)
            editTextSubject
                .debounce(2000, TimeUnit.MILLISECONDS)
                .subscribe {
                    getLocations(_searchFilter.value.toString())
                }
        }
    }

    fun getLocationsOld(name: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val locationsResult = getLocationsUseCase.invoke(name)) {
                is Resultss.Success -> {
                    _remoteLocations.clear()
                    _remoteLocations.addAll(locationsResult.data)

                    _locations.value = _remoteLocations
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

    fun getLocations(queryString: String){
        val res = repository.getSearchResultStream(queryString)
        val el = res.map { pagingData ->
             pagingData.map{ it.mapToDTO() }
        }
        val tr = res.asLiveData(Dispatchers.Main)
        //_locations.value = tr
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val locationsResult = getLocationsUseCase.invoke(queryString)) {
                is Resultss.Success -> {
                    _remoteLocations.clear()
                    _remoteLocations.addAll(locationsResult.data)

                    _locations.value = _remoteLocations
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

    fun getLocations11(queryString: String): Flow<PagingData<LocationEntity>> =
        repository.getSearchResultStream(queryString)
            //.map { pagingData -> pagingData.map { UiModelLocat.LocatItem(it) } }

    fun scrollMore(
        visibleItemCount: Int,
        lastVisibleItemPosition: Int,
        totalItemCount: Int
    ) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount){
            Log.d("TAG","Need load ${visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD}  $totalItemCount")
        }
    }

    fun getLocationsRx(name: String) {

        val observer = object : SingleObserver<List<LocationDTO>> {
            override fun onSuccess(response: List<LocationDTO>) {
                _locations.value = response
            }

            override fun onError(e: Throwable) {
                _error.value = e.message
            }

            override fun onSubscribe(d: Disposable) {}
        }

        getLocationsRxUseCase.invoke(name)
            .map { resp ->
                resp.body()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()//observer)
    }

    class LocationsViewModelFactory(
        private val getLocationsUseCase: GetLocationsUseCase,
        private val getLocationsRxUseCase: GetLocationsRxUseCase,
        private val repository: LocationsRepositoryRM
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LocationsViewModel(
                getLocationsUseCase,
                getLocationsRxUseCase,
                repository
            ) as T
        }
    }
}