package com.vertigo.marspatrol.domain.repository

import com.vertigo.marspatrol.domain.model.MarsPhoto

interface MarsRoverPhotoRepository {

    suspend fun getDefaultPhotoList(sol: String, roverName: String): List<MarsPhoto>

    suspend fun getNeedPhotoList(data: String, roverName: String): List<MarsPhoto>
}