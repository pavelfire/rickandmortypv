package com.vk.directop.rickandmortypv.data.repositories.characters

import com.vk.directop.rickandmortypv.data.mappers.CharacterApiResponseMapper
import com.vk.directop.rickandmortypv.data.remote.RickAndMortyApi
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterDTO
import com.vk.directop.rickandmortypv.data.remote.dto.character.CharacterResponse
import com.vk.directop.rickandmortypv.domain.common.Resultss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CharactersRemoteDataSourceImpl(
    private val service: RickAndMortyApi,
    private val mapper: CharacterApiResponseMapper,
) : CharactersRemoteDataSource {

    override suspend fun getCharacters(): Resultss<List<CharacterDTO>> =
        withContext(Dispatchers.IO){
            try {
                val response = service.getCharacters()
                if (response.isSuccessful){
                    return@withContext Resultss.Success(mapper.toMyList(response.body()!!))
                } else {
                    return@withContext Resultss.Error(Exception(response.message()))
                }
            }catch (e: Exception){
                return@withContext Resultss.Error(e)
            }
        }

}