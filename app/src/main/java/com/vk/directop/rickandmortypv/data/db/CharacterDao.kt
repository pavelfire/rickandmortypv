package com.vk.directop.rickandmortypv.data.db

import androidx.room.*
import com.vk.directop.rickandmortypv.data.entities.CHARACTERS_TABLE_NAME
import com.vk.directop.rickandmortypv.data.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(character: CharacterEntity)

    @Query("SELECT * FROM $CHARACTERS_TABLE_NAME")
    fun getSavedCharacters(): Flow<List<CharacterEntity>>

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)
}