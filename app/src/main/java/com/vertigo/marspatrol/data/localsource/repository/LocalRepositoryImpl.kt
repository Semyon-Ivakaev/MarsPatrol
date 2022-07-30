package com.vertigo.marspatrol.data.localsource.repository

import android.util.Log
import com.vertigo.marspatrol.data.localsource.database.MarsPhotoDao
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val marsPhotoDao: MarsPhotoDao
): LocalRepository {
    override suspend fun addToFavorites(photo: MarsPhoto) {
        marsPhotoDao.insert(marsPhoto = photo)
    }

    override suspend fun getAllFavoritesPhoto():List<MarsPhoto> {
        return marsPhotoDao.getAllLocalPhoto()
    }

    override suspend fun checkPhotoInDB(url: String): Boolean {
        return marsPhotoDao.getLocalPhoto(originalUrl = url)
    }

    override suspend fun removeFromFavorite(photo: MarsPhoto) {
        Log.v("App", "Remove")
        marsPhotoDao.deleteLocalPhoto(marsPhoto = photo)
    }
}