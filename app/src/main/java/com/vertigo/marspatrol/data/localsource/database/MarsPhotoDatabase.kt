package com.vertigo.marspatrol.data.localsource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vertigo.marspatrol.domain.model.MarsPhoto

@Database(entities = [MarsPhoto::class], version = 1, exportSchema = true)
abstract class MarsPhotoDatabase: RoomDatabase() {
    abstract fun getMarsPhotoDao(): MarsPhotoDao
}