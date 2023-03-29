package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(name: String) = charactersRepository.getRemoteCharacters(name = name)
}