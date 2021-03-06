package com.vertigo.marspatrol.domain.repository

import com.vertigo.marspatrol.domain.model.MarsPhoto

interface LocalRepository {
    suspend fun addToFavorites(photo: MarsPhoto)

    suspend fun getAllFavoritesPhoto(): List<MarsPhoto>

    suspend fun checkPhotoInDB(url: String): Boolean

    suspend fun removeFromFavorite(photo: MarsPhoto)
}