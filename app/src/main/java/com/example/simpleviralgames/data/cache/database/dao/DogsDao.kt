package com.example.simpleviralgames.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simpleviralgames.data.cache.database.model.Dogs
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {
    @Insert
    suspend fun insertNewDogImage(dog: Dogs): Long

    @Query("SELECT * FROM Dogs ORDER BY timeStamp DESC LIMIT 20")
    fun getDogsImages(): Flow<List<Dogs>>

    @Query("DELETE FROM Dogs WHERE timeStamp NOT IN (SELECT timeStamp FROM Dogs ORDER BY timeStamp DESC LIMIT 20)")
    suspend fun clearImagesAfter20Entries()

    @Query("DELETE FROM Dogs")
    suspend fun clearAllImages()
}