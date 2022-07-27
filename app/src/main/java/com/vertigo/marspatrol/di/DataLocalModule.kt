package com.vertigo.marspatrol.di

import android.content.Context
import androidx.room.Room
import com.vertigo.marspatrol.data.localsource.database.MarsPhotoDao
import com.vertigo.marspatrol.data.localsource.database.MarsPhotoDatabase
import com.vertigo.marspatrol.data.localsource.repository.LocalMarsRoverPhotoRepositoryImpl
import com.vertigo.marspatrol.domain.repository.LocalMarsRoverPhotoRepository
import com.vertigo.marspatrol.domain.usecase.marsphoto.AddToFavoriteUseCase
import com.vertigo.marspatrol.domain.usecase.marsphoto.CheckPhotoInDBUseCase
import com.vertigo.marspatrol.domain.usecase.marsphoto.RemoveFromFavoriteUseCase
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

    @Provides
    fun provideLocalMarsRoverPhotoRepository(marsPhotoDao: MarsPhotoDao): LocalMarsRoverPhotoRepository {
        return LocalMarsRoverPhotoRepositoryImpl(marsPhotoDao = marsPhotoDao)
    }

    @Provides
    fun provideAddToFavoriteUseCase(localMarsRoverPhotoRepository: LocalMarsRoverPhotoRepository): AddToFavoriteUseCase {
        return AddToFavoriteUseCase(localMarsRoverPhotoRepository = localMarsRoverPhotoRepository)
    }

    @Provides
    fun provideCheckPhotoInDBUseCase(localMarsRoverPhotoRepository: LocalMarsRoverPhotoRepository): CheckPhotoInDBUseCase {
        return CheckPhotoInDBUseCase(localMarsRoverPhotoRepository = localMarsRoverPhotoRepository)
    }

    @Provides
    fun provideRemoveFromFavoriteUseCase(localMarsRoverPhotoRepository: LocalMarsRoverPhotoRepository): RemoveFromFavoriteUseCase {
        return RemoveFromFavoriteUseCase(localMarsRoverPhotoRepository = localMarsRoverPhotoRepository)
    }
}