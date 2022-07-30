package com.vertigo.marspatrol.di

import com.vertigo.marspatrol.data.remotesource.repository.RemoteRepositoryImpl
import com.vertigo.marspatrol.domain.repository.RemoteRepository
import com.vertigo.marspatrol.domain.usecase.marstemp.GetMarsTempListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataRemoteModule {

    @Provides
    fun provideRemoteRepository(): RemoteRepository {
        return RemoteRepositoryImpl()
    }

    @Provides
    fun provideGetMarsTempListUseCase(remoteRepository: RemoteRepository): GetMarsTempListUseCase {
        return GetMarsTempListUseCase(remoteRepository = remoteRepository)
    }
}