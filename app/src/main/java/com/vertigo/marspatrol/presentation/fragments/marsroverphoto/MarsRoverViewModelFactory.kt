package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vertigo.marspatrol.data.remotesource.repository.RemoteRepositoryImpl
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetDefaultSolForMarsRoverUseCase
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetNeededMatsPhotoListUseCase

class MarsRoverViewModelFactory: ViewModelProvider.Factory {
    private val remoteRepositoryImpl by lazy {
        RemoteRepositoryImpl()
    }

    private val getNeededMatsPhotoListUseCase by lazy {
        GetNeededMatsPhotoListUseCase(remoteRepository = remoteRepositoryImpl)
    }

    private val getDefaultSolForMarsRoverUseCase by lazy {
        GetDefaultSolForMarsRoverUseCase(remoteRepository = remoteRepositoryImpl)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MarsRoverPhotoViewModel(
            getNeededMatsPhotoListUseCase = getNeededMatsPhotoListUseCase,
            getDefaultSolForMarsRoverUseCase = getDefaultSolForMarsRoverUseCase) as T
    }
}