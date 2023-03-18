package com.example.simpleviralgames.data.repository

import com.example.simpleviralgames.data.cache.database.dao.DogsDao
import com.example.simpleviralgames.domain.repository.PreviewDogsRepository
import com.example.simpleviralgames.domain.state.DataState
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PreviewDogsRepositoryImpl @Inject constructor(
    private val dogsDao: DogsDao
) : PreviewDogsRepository {
    override fun getCachedDogImage() = flow {
        dogsDao.getDogsImages().collect {
            emit(DataState.success(it))
        }
    }.catch {
        emit(DataState.error(it.message ?: "Unknown Error"))
    }

    override fun clearCachedDogImage() = flow {
        dogsDao.clearAllImages()
        emit(DataState.success(true))
    }.catch {
        emit(DataState.error(it.message ?: "Unknown Error"))
    }
}