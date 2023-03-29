package com.vk.directop.rickandmortypv.data.db

import androidx.room.*
import com.vk.directop.rickandmortypv.data.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(character: CharacterEntity)

    @Query("SELECT * FROM tablecharacters")
    fun getSavedCharacters(): Flow<List<CharacterEntity>>

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)
}