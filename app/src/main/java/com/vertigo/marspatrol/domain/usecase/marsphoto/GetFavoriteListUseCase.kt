package com.vertigo.marspatrol.domain.usecase.marsphoto

import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.repository.LocalRepository
import javax.inject.Inject

class GetFavoriteListUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend fun execute(): List<MarsPhoto> {
        return localRepository.getAllFavoritesPhoto()
    }
}