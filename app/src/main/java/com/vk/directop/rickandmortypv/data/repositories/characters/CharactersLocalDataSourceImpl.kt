package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.db.CharacterDao
import com.vk.directop.rickandmortypv.data.mappers.CharacterEntityMapper
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersLocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val dispatcher: CoroutineDispatcher,
    private val characterEntityMapper: CharacterEntityMapper
) : CharactersLocalDataSource {

    override suspend fun characterSaveToDb(character: Array<CharacterDTO>) =
        withContext(dispatcher) {
            for (element in character) {
                characterDao.saveCharacter(characterEntityMapper.toCharacterEntity(element))
            }
        }

    override suspend fun characterDeleteFromDb(character: CharacterDTO) = withContext(dispatcher) {
        characterDao.deleteCharacter(characterEntityMapper.toCharacterEntity(character))
    }

    override suspend fun getDbCharacters(name: String): Flow<List<CharacterDTO>> {
        val savedCharactersFlow = characterDao.getSavedCharacters()
        return if (name != "") {
            savedCharactersFlow.map { list ->
                list.filter {
                    it.name.uppercase() == name.uppercase()
                }.map { element ->
                    characterEntityMapper.toCharacterDTO(element)
                }
            }
        } else {
            savedCharactersFlow.map { list ->
                list.map { element ->
                    characterEntityMapper.toCharacterDTO(element)
                }
            }
        }
    }
}