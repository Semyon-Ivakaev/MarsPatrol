package com.vertigo.marspatrol.domain.usecase.marsphoto

import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsRover
import com.vertigo.marspatrol.domain.repository.RemoteRepository

class GetDefaultSolForMarsRoverUseCase(private val remoteRepository: RemoteRepository) {
    suspend fun execute(roverName: String): ApiResponse<MarsRover?> {
        return remoteRepository.getDefaultSolForMarsRover(roverName = roverName)
    }
}