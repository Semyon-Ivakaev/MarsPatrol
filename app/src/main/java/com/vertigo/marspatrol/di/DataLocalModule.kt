package com.vertigo.marspatrol.di

import android.content.Context
import androidx.room.Room
import com.vertigo.marspatrol.data.localsource.database.MarsPhotoDao
import com.vertigo.marspatrol.data.localsource.database.MarsPhotoDatabase
import com.vertigo.marspatrol.data.localsource.repository.LocalRepositoryImpl
import com.vertigo.marspatrol.domain.repository.LocalRepository
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
    fun provideLocalMarsRoverPhotoRepository(marsPhotoDao: MarsPhotoDao): LocalRepository {
        return LocalRepositoryImpl(marsPhotoDao = marsPhotoDao)
    }

    @Provides
    fun provideAddToFavoriteUseCase(localRepository: LocalRepository): AddToFavoriteUseCase {
        return AddToFavoriteUseCase(localRepository = localRepository)
    }

    @Provides
    fun provideCheckPhotoInDBUseCase(localRepository: LocalRepository): CheckPhotoInDBUseCase {
        return CheckPhotoInDBUseCase(localRepository = localRepository)
    }

    @Provides
    fun provideRemoveFromFavoriteUseCase(localRepository: LocalRepository): RemoveFromFavoriteUseCase {
        return RemoveFromFavoriteUseCase(localRepository = localRepository)
    }
}