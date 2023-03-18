package com.example.simpleviralgames.presentation.screen.generateDogs

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.LruCache
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.simpleviralgames.domain.repository.GenerateDogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val CACHE_IMAGE_SIZE = 1024 * 1024

@HiltViewModel
class GenerateDogsViewModel @Inject constructor(
    private val generateDogsRepository: GenerateDogsRepository
) : ViewModel() {
    val loading = mutableStateOf(false)
    val imageUrl = mutableStateOf<Bitmap?>(null)
    private val bitmapCache = LruCache<String, Bitmap>(CACHE_IMAGE_SIZE)

    fun onGenerateButton(context: Context) {
        loading.value = true
        generateDogsRepository.getRandomDogImage().onEach { dataState ->
            dataState.data?.let { response ->
                Glide.with(context)
                    .asBitmap()
                    .load(response.message)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            bitmapCache.put(response.message, resource)
                            imageUrl.value = resource
                            loading.value = false
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            // todo
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            loading.value = false
                        }
                    })
            }
            dataState.error?.let {
                loading.value = false
            }
        }.launchIn(viewModelScope)
    }
}