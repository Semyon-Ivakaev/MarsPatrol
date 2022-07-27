package com.vertigo.marspatrol.presentation.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.usecase.marsphoto.AddToFavoriteUseCase
import com.vertigo.marspatrol.domain.usecase.marsphoto.CheckPhotoInDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val checkPhotoInDBUseCase: CheckPhotoInDBUseCase
) : ViewModel() {

    private val _detailPhotoViewModel: MutableLiveData<MarsPhoto> = MutableLiveData()
    val detailViewModel: LiveData<MarsPhoto> get() = _detailPhotoViewModel
    private val _photoInDB: MutableLiveData<Boolean> = MutableLiveData()
    val photoInDB: LiveData<Boolean> get() = _photoInDB

    fun setDetailPhoto(photo: MarsPhoto) {
        viewModelScope.launch {
            photo.isFavorite = checkPhotoInDBUseCase.execute(marsPhoto = photo)
            _detailPhotoViewModel.value = photo
        }
    }

    fun actionWithPhoto() {
        if (_detailPhotoViewModel.value?.isFavorite == true) {
        } else {
            addPhotoToFavorite()

        }
    }

    private fun addPhotoToFavorite() {
        _detailPhotoViewModel.value?.isFavorite = true
        viewModelScope.launch {
            _detailPhotoViewModel.value?.let { addToFavoriteUseCase.execute(it) }
        }
    }
}