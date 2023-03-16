package com.example.simpleviralgames.data.network.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenerateDogsResponse(
    val message: String,
    val status: String
)
