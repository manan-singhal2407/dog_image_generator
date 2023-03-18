package com.example.simpleviralgames.data.cache.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Dogs(
    @PrimaryKey
    val timeStamp: Long,
    val imageUrl: String,
    val imageData: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dogs

        if (timeStamp != other.timeStamp) return false
        if (imageUrl != other.imageUrl) return false
        if (!imageData.contentEquals(other.imageData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timeStamp.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + imageData.contentHashCode()
        return result
    }
}
