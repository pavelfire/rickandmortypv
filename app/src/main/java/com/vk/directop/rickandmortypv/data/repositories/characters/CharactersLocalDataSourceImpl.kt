package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.db.CharacterDao
import com.vk.directop.rickandmortypv.data.mappers.CharacterEntityMapper
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CharactersLocalDataSourceImpl(
    private val characterDao: CharacterDao,
    private val dispatcher: CoroutineDispatcher,
    private val characterEntityMapper: CharacterEntityMapper
) : CharactersLocalDataSource {

    override suspend fun characterSaveToDb(character: CharacterDTO) = withContext(dispatcher) {
        characterDao.saveCharacter(characterEntityMapper.toCharacterEntity(character))
    }

    override suspend fun characterDeleteFromDb(character: CharacterDTO) = withContext(dispatcher) {
        characterDao.deleteCharacter(characterEntityMapper.toCharacterEntity(character))
    }

    override suspend fun getDbCharacters(): Flow<List<CharacterDTO>> {
        val savedCharactersFlow = characterDao.getSavedCharacters()
        return savedCharactersFlow.map { list ->
            list.map { element ->
                characterEntityMapper.toCharacterDTO(element)
            }
        }
    }
}