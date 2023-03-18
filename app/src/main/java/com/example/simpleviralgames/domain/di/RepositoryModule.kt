package com.example.simpleviralgames.domain.di

import com.example.simpleviralgames.data.cache.database.dao.DogsDao
import com.example.simpleviralgames.data.network.service.GenerateDogsService
import com.example.simpleviralgames.data.repository.GenerateDogsRepositoryImpl
import com.example.simpleviralgames.domain.repository.GenerateDogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideGenerateDogsRepository(
        generateDogsService: GenerateDogsService,
        dogsDao: DogsDao
    ): GenerateDogsRepository {
        return GenerateDogsRepositoryImpl(
            generateDogsService = generateDogsService,
            dogsDao = dogsDao
        )
    }
}