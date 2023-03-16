package com.vk.directop.rickandmortypv.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.common.Resultss
import com.vk.directop.rickandmortypv.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
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

                    //val charactersFlow =
                }
                is Resultss.Error -> {
                    _dataLoading.postValue(false)
                    _characters.value = emptyList()
                    _error.postValue(charactersResult.exception.message)
                }
            }
        }
    }
}