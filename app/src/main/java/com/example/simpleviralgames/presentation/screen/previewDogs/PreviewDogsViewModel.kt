package com.example.simpleviralgames.presentation.screen.previewDogs

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleviralgames.data.cache.database.model.Dogs
import com.example.simpleviralgames.domain.repository.PreviewDogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class PreviewDogsViewModel @Inject constructor(
    private val previewDogsRepository: PreviewDogsRepository
) : ViewModel() {
    var dogsList = mutableStateListOf<Dogs>()

    init {
        loadDogsImages()
    }

    private fun loadDogsImages() {
        previewDogsRepository.getCachedDogImage().onEach { dataState ->
            dataState.data?.let { response ->
                dogsList.addAll(response)
            }
            dataState.error?.let {
            }
        }.launchIn(viewModelScope)
    }

    fun onClearDogsButton() {
        previewDogsRepository.clearCachedDogImage().onEach { dataState ->
            dataState.data?.let {
                dogsList.clear()
            }
            dataState.error?.let {
            }
        }.launchIn(viewModelScope)
    }
}