package com.example.simpleviralgames.presentation.screen.generateDogs

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.simpleviralgames.data.cache.database.model.Dogs
import com.example.simpleviralgames.domain.repository.GenerateDogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val IMAGE_COMPRESSION_QUALITY = 100

@HiltViewModel
class GenerateDogsViewModel @Inject constructor(
    private val generateDogsRepository: GenerateDogsRepository
) : ViewModel() {
    val loading = mutableStateOf(false)
    val imageUrl = mutableStateOf<Bitmap?>(null)

    fun onGenerateButton(context: Context) {
        loading.value = true
        generateDogsRepository.getRandomDogImage().onEach { dataState ->
            dataState.data?.let { response ->
                convertImageUrlToBitmap(context, response.message)
            }
            dataState.error?.let {
                loading.value = false
            }
        }.launchIn(viewModelScope)
    }

    private fun convertImageUrlToBitmap(context: Context, url: String) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    imageUrl.value = resource
                    loading.value = false
                    val dogs =
                        Dogs(System.currentTimeMillis(), url, convertBitmapToByteArray(resource))
                    saveImageToCache(dogs)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    //
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    loading.value = false
                }
            })
    }

    private fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, IMAGE_COMPRESSION_QUALITY, stream)
        return stream.toByteArray()
    }

    private fun saveImageToCache(dogs: Dogs) {
        generateDogsRepository.saveDogImageToRoom(dogs).onEach {}.launchIn(viewModelScope)
    }
}