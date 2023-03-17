package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository

class GetSavedCharactersUseCase(private val charactersRepository: CharactersRepository) {
    //suspend operator fun invoke() = charactersRepository.getSavedCharacters()
}