package com.example.simpleviralgames.domain.repository

import com.example.simpleviralgames.data.cache.database.model.Dogs
import com.example.simpleviralgames.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface PreviewDogsRepository {
    fun getCachedDogImage(): Flow<DataState<List<Dogs>>>

    fun clearCachedDogImage(): Flow<DataState<Boolean>>
}