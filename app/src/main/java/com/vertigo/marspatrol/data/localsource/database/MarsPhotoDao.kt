package com.vertigo.marspatrol.data.localsource.database

import androidx.room.*
import com.vertigo.marspatrol.domain.model.MarsPhoto

@Dao
interface MarsPhotoDao {

    @Query("SELECT * FROM mars_photo_table ORDER BY earth_date DESC")
    suspend fun getAllLocalPhoto(): List<MarsPhoto>

    @Query("SELECT EXISTS (SELECT * FROM mars_photo_table WHERE img_url = :originalUrl)")
    suspend fun getLocalPhoto(originalUrl: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(marsPhoto: MarsPhoto)

    @Delete
    suspend fun deleteLocalPhoto(marsPhoto: MarsPhoto)
}