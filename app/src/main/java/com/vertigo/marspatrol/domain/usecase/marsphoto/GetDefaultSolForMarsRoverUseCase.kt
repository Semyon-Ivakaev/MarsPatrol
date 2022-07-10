package com.vertigo.marspatrol.domain.usecase.marsphoto

import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsRover
import com.vertigo.marspatrol.domain.repository.MarsRoverPhotoRepository

class GetDefaultSolForMarsRoverUseCase(private val marsRoverPhotoRepository: MarsRoverPhotoRepository) {
    suspend fun execute(roverName: String): ApiResponse<MarsRover?> {
        return marsRoverPhotoRepository.getDefaultSolForMarsRover(roverName = roverName)
    }
}