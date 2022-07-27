package com.vertigo.marspatrol.domain.usecase.marsphoto

import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.repository.LocalMarsRoverPhotoRepository
import javax.inject.Inject

class GetFavoriteListUseCase @Inject constructor(
    private val localMarsRoverPhotoRepository: LocalMarsRoverPhotoRepository
) {
    suspend fun execute(): List<MarsPhoto> {
        return localMarsRoverPhotoRepository.getAllFavoritesPhoto()
    }
}