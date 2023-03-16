package com.example.simpleviralgames.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpleviralgames.data.cache.database.dao.DogsDao
import com.example.simpleviralgames.data.cache.database.model.Dogs

@Database(entities = [Dogs::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun dogsDao(): DogsDao

    companion object {
        const val DATABASE_NAME: String = "SimpleViralGames"
    }
}