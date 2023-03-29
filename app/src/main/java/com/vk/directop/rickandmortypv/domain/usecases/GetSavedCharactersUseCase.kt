package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.data.repositories.characters.CharactersParams
import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetSavedCharactersUseCase @Inject constructor(val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(charactersParams: CharactersParams) =
        charactersRepository.getSavedCharacters(charactersParams)
}