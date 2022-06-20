package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vertigo.marspatrol.data.remotesource.repository.MarsRoverPhotoRepositoryImpl
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetDefaultMarsPhotoListUseCase
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetNeededMatsPhotoListUseCase

class MarsRoverViewModelFactory: ViewModelProvider.Factory {
    private val marsRoverPhotoRepositoryImpl by lazy {
        MarsRoverPhotoRepositoryImpl()
    }

    private val getDefaultMarsPhotoListUseCase by lazy {
        GetDefaultMarsPhotoListUseCase(marsRoverPhotoRepository = marsRoverPhotoRepositoryImpl)
    }

    private val getNeededMatsPhotoListUseCase by lazy {
        GetNeededMatsPhotoListUseCase(marsRoverPhotoRepository = marsRoverPhotoRepositoryImpl)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MarsRoverPhotoViewModel(
            getDefaultMarsPhotoListUseCase = getDefaultMarsPhotoListUseCase,
            getNeededMatsPhotoListUseCase = getNeededMatsPhotoListUseCase) as T
    }
}