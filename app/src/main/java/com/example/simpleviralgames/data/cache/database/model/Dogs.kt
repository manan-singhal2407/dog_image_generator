package com.example.simpleviralgames.data.cache.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Dogs(
    @PrimaryKey
    val timeStamp: Long,
    val imageUrl: String
)
