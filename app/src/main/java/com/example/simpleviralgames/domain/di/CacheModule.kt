package com.example.simpleviralgames.domain.di

import androidx.room.Room
import com.example.simpleviralgames.data.cache.database.LocalDatabase
import com.example.simpleviralgames.data.cache.database.dao.DogsDao
import com.example.simpleviralgames.domain.SimpleViralGames
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(simpleViralGames: SimpleViralGames): LocalDatabase {
        return Room
            .databaseBuilder(
                simpleViralGames,
                LocalDatabase::class.java,
                LocalDatabase.DATABASE_NAME
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideDogsDao(db: LocalDatabase): DogsDao = db.dogsDao()
}