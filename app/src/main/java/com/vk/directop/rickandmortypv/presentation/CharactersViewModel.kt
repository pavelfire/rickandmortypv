package com.vk.directop.rickandmortypv.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.usecases.GetCharactersUseCase
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    //private val getSavedCharactersUseCase: GetSavedCharactersUseCase,
    //private val mapper: CharacterApiResponseMapper
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _characters = MutableLiveData<List<CharacterDTO>>()
    val characters: LiveData<List<CharacterDTO>> = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteCharacters = arrayListOf<CharacterDTO>()

    private val _searchFilter = MutableLiveData("")
    val searchFilter: LiveData<String> = _searchFilter

    private val editTextSubject = PublishSubject.create<String>()

    @SuppressLint("CheckResult")
    fun searchName(text: String, sendButton: Boolean) {

        _searchFilter.value = text

        if (sendButton) getCharacters(_searchFilter.value.toString())
        else {
            editTextSubject.onNext(text)
            editTextSubject
                .debounce(2000, TimeUnit.MILLISECONDS)
                .subscribe {
                    getCharacters(_searchFilter.value.toString())
                }
        }
    }

    fun getCharacters(name: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val charactersResult = getCharactersUseCase.invoke(name)) {
                is Resultss.Success -> {
                    _remoteCharacters.clear()
                    _remoteCharacters.addAll(charactersResult.data)

                    _characters.value = _remoteCharacters
                    _dataLoading.postValue(false)

//                    val charactersFlow = getSavedCharactersUseCase.invoke()
//                    charactersFlow.collect{ charact ->
//                        _characters.value = _remoteCharacters
//                     _dataLoading.postValue(false)
//                    }
                }
                is Resultss.Error -> {
                    _dataLoading.postValue(false)
                    _characters.value = emptyList()
                    _error.postValue(charactersResult.exception.message)
                    _dataLoading.postValue(false)
                }
            }
        }
    }

    class CharactersViewModelFactory(
        private val getCharactersUseCase: GetCharactersUseCase,
        //private val getSavedCharactersUseCase: GetSavedCharactersUseCase,
        //private val mapper: CharacterApiResponseMapper
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CharactersViewModel(
                getCharactersUseCase,
                //getSavedCharactersUseCase
                //mapper
            ) as T
        }
    }
}