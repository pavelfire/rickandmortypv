package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke() = charactersRepository.getRemoteCharacters()
}