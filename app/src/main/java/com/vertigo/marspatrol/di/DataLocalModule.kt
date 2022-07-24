package com.vertigo.marspatrol.di

import android.content.Context
import androidx.room.Room
import com.vertigo.marspatrol.data.localsource.database.MarsPhotoDao
import com.vertigo.marspatrol.data.localsource.database.MarsPhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataLocalModule {

    @Provides
    @Singleton
    fun provideMarsPhotoDatabase(@ApplicationContext context: Context): MarsPhotoDatabase {
        return Room.databaseBuilder(context, MarsPhotoDatabase::class.java, "mars_photo_database").build()
    }

    @Provides
    fun provideMarsPhotoDao(appDatabase: MarsPhotoDatabase): MarsPhotoDao {
        return appDatabase.getMarsPhotoDao()
    }
}