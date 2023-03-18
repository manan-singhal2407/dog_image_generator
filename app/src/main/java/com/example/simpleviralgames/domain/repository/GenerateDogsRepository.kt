package com.example.simpleviralgames.domain.repository

import com.example.simpleviralgames.data.cache.database.model.Dogs
import com.example.simpleviralgames.data.network.model.response.GenerateDogsResponse
import com.example.simpleviralgames.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface GenerateDogsRepository {
    fun getRandomDogImage(): Flow<DataState<GenerateDogsResponse>>

    fun saveDogImageToRoom(dogs: Dogs): Flow<DataState<Boolean>>
}