package com.vertigo.marspatrol.domain.usecase.marstemp

import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsTemp
import com.vertigo.marspatrol.domain.repository.RemoteRepository
import javax.inject.Inject

class GetMarsTempListUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {
    suspend fun execute(): ApiResponse<List<MarsTemp>> {
        return remoteRepository.getTemperature()
    }
}