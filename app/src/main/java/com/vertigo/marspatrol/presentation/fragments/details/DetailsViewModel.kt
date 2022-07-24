package com.vertigo.marspatrol.presentation.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vertigo.marspatrol.domain.model.MarsPhoto

class DetailsViewModel: ViewModel() {

    private val _detailPhotoViewModel: MutableLiveData<MarsPhoto> = MutableLiveData()
    val detailViewModel: LiveData<MarsPhoto> get() = _detailPhotoViewModel

    fun setDetailPhoto(photo: MarsPhoto) {
        _detailPhotoViewModel.value = photo
    }
}