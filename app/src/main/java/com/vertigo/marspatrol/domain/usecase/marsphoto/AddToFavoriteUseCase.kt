package com.vertigo.marspatrol.domain.usecase.marsphoto

import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.repository.LocalMarsRoverPhotoRepository

class AddToFavoriteUseCase(private val localMarsRoverPhotoRepository: LocalMarsRoverPhotoRepository) {
    suspend fun execute(marsPhoto: MarsPhoto) {
        localMarsRoverPhotoRepository.addToFavorites(photo = marsPhoto)
    }
}