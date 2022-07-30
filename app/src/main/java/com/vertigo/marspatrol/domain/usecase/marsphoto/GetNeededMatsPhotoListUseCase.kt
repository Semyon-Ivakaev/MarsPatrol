package com.vertigo.marspatrol.domain.usecase.marsphoto

import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.repository.RemoteRepository

class GetNeededMatsPhotoListUseCase(private val remoteRepository: RemoteRepository) {
    suspend fun execute(data: String, roverName: String): ApiResponse<List<MarsPhoto>> {
        return remoteRepository.getNeededPhotoList(data = data, roverName = roverName)
    }
}