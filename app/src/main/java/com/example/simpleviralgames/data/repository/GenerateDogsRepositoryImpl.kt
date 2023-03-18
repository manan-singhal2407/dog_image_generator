package com.example.simpleviralgames.data.repository

import com.example.simpleviralgames.data.cache.database.dao.DogsDao
import com.example.simpleviralgames.data.cache.database.model.Dogs
import com.example.simpleviralgames.data.network.service.GenerateDogsService
import com.example.simpleviralgames.domain.repository.GenerateDogsRepository
import com.example.simpleviralgames.domain.state.DataState
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GenerateDogsRepositoryImpl @Inject constructor(
    private val generateDogsService: GenerateDogsService,
    private val dogsDao: DogsDao
) : GenerateDogsRepository {
    override fun getRandomDogImage() = flow {
        emit(DataState.loading())
        val generateDogsResponse = generateDogsService.getDogImage()
        emit(DataState.success(generateDogsResponse))
    }.catch {
        emit(DataState.error(it.message ?: "Unknown Error"))
    }

    override fun saveDogImageToRoom(dogs: Dogs) = flow {
        dogsDao.insertNewDogImage(dogs)
        dogsDao.clearImagesAfter20Entries()
        emit(DataState.success(true))
    }.catch {
        emit(DataState.error(it.message ?: "Unknown Error"))
    }
}