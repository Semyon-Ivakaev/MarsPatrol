package com.vertigo.marspatrol.domain.usecase.marsphoto

import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.repository.MarsRoverPhotoRepository

class GetDefaultMarsPhotoListUseCase(private val marsRoverPhotoRepository: MarsRoverPhotoRepository) {
    suspend fun execute(sol: String, roverName: String): ApiResponse<List<MarsPhoto>> {
        return marsRoverPhotoRepository.getDefaultPhotoList(sol = sol, roverName = roverName)
    }
}