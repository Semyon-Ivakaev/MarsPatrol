package com.vertigo.marspatrol.domain.usecase.marsphoto

import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.repository.LocalRepository
import javax.inject.Inject

class CheckPhotoInDBUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend fun execute(marsPhoto: MarsPhoto): Boolean {
        return localRepository.checkPhotoInDB(url = marsPhoto.img_url)
    }
}