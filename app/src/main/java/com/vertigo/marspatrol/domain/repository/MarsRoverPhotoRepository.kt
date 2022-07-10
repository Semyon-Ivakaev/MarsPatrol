package com.vertigo.marspatrol.domain.repository

import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.model.MarsRover

interface MarsRoverPhotoRepository {

    suspend fun getDefaultPhotoList(sol: String, roverName: String): ApiResponse<List<MarsPhoto>>

    suspend fun getNeedPhotoList(data: String, roverName: String): ApiResponse<List<MarsPhoto>>

    suspend fun getDefaultSolForMarsRover(roverName: String): ApiResponse<MarsRover?>
}