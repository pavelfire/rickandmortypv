package com.vk.directop.rickandmortypv.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO

class CharactersViewModel(

) : ViewModel(){

    private val _characters = MutableLiveData<List<CharacterDTO>>()
    val characters: LiveData<List<CharacterDTO>> = _characters

    init {

    }
}