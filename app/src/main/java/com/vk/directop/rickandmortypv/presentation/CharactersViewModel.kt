package com.vk.directop.rickandmortypv.presentation

import androidx.lifecycle.*
import com.vk.directop.rickandmortypv.data.mappers.CharacterApiResponseMapper
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.remote.dto.character.Location
import com.vk.directop.rickandmortypv.data.remote.dto.character.Origin
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.usecases.GetCharactersUseCase
import com.vk.directop.rickandmortypv.domain.usecases.GetSavedCharactersUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

    fun getCharacters() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val charactersResult = getCharactersUseCase.invoke()) {
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