package com.example.simpleviralgames.data.network.service

import com.example.simpleviralgames.data.network.model.response.GenerateDogsResponse
import retrofit2.http.GET

interface GenerateDogsService {
    @GET("api/breeds/image/random")
    suspend fun getDogImage(): GenerateDogsResponse
}
