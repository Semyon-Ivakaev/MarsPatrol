package com.vertigo.marspatrol.domain.repository

import com.vertigo.marspatrol.domain.model.MarsPhoto

interface LocalMarsRoverPhotoRepository {
    suspend fun addToFavorites(photo: MarsPhoto)
}