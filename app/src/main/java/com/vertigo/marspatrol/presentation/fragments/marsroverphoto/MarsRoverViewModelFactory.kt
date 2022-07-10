package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vertigo.marspatrol.data.remotesource.repository.MarsRoverPhotoRepositoryImpl
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetDefaultSolForMarsRoverUseCase
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetNeededMatsPhotoListUseCase

class MarsRoverViewModelFactory: ViewModelProvider.Factory {
    private val marsRoverPhotoRepositoryImpl by lazy {
        MarsRoverPhotoRepositoryImpl()
    }

    private val getNeededMatsPhotoListUseCase by lazy {
        GetNeededMatsPhotoListUseCase(marsRoverPhotoRepository = marsRoverPhotoRepositoryImpl)
    }

    private val getDefaultSolForMarsRoverUseCase by lazy {
        GetDefaultSolForMarsRoverUseCase(marsRoverPhotoRepository = marsRoverPhotoRepositoryImpl)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MarsRoverPhotoViewModel(
            getNeededMatsPhotoListUseCase = getNeededMatsPhotoListUseCase,
            getDefaultSolForMarsRoverUseCase = getDefaultSolForMarsRoverUseCase) as T
    }
}