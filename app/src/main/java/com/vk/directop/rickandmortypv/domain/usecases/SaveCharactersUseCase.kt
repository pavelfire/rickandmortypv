package com.vk.directop.rickandmortypv.domain.usecases

import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.domain.repositories.CharactersRepository
import javax.inject.Inject

class SaveCharactersUseCase @Inject constructor(val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterDTO: Array<CharacterDTO>) = charactersRepository.characterSaveToDb(characterDTO)
}