package com.vk.directop.rickandmortypv.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.vk.directop.rickandmortypv.data.remote.dto.location.LocationDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.usecases.GetLocationsFlowUseCase
import com.vk.directop.rickandmortypv.domain.usecases.GetLocationsRxUseCase
import com.vk.directop.rickandmortypv.domain.usecases.GetLocationsUseCase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val VISIBLE_THRESHOLD = -20

class LocationsViewModel(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getLocationsRxUseCase: GetLocationsRxUseCase,
    private val getLocationsFlowUseCase: GetLocationsFlowUseCase,
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _locations = MutableLiveData<List<LocationDTO>>()
    val locations: LiveData<List<LocationDTO>> = _locations

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _searchFilter = MutableLiveData("")
    val searchFilter: LiveData<String> = _searchFilter

    private val editTextSubject = PublishSubject.create<String>()

    @SuppressLint("CheckResult")
    fun searchName(text: String, sendButton: Boolean) {

        _searchFilter.value = text

        if (sendButton) getLocationsRx(_searchFilter.value.toString())
        else {
            editTextSubject.onNext(text)
            editTextSubject
                .debounce(2000, TimeUnit.MILLISECONDS)
                .subscribe {
                    getLocationsRx(_searchFilter.value.toString())
                }
        }
    }

    fun scrollMore(
        visibleItemCount: Int,
        lastVisibleItemPosition: Int,
        totalItemCount: Int
    ) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {

        }
    }

    fun getLocationsRx(name: String) {

        val observer = object : SingleObserver<List<LocationDTO>> {
            override fun onSuccess(response: List<LocationDTO>) {
                _locations.value = response
                _dataLoading.postValue(false)
            }

            override fun onError(e: Throwable) {
                _error.value = e.message
                _dataLoading.postValue(false)
            }

            override fun onSubscribe(d: Disposable) {}
        }

        getLocationsRxUseCase.invoke(name)
            .map { response ->
                response.body()!!.results
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    class LocationsViewModelFactory @Inject constructor(
        private val getLocationsUseCase: GetLocationsUseCase,
        private val getLocationsRxUseCase: GetLocationsRxUseCase,
        private val getLocationsFlowUseCase: GetLocationsFlowUseCase,
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LocationsViewModel(
                getLocationsUseCase,
                getLocationsRxUseCase,
                getLocationsFlowUseCase
            ) as T
        }
    }
}