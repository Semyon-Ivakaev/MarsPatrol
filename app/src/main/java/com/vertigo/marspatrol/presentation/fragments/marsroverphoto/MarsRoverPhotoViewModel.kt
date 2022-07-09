package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetDefaultMarsPhotoListUseCase
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetNeededMatsPhotoListUseCase
import kotlinx.coroutines.launch

class MarsRoverPhotoViewModel(
    private val getDefaultMarsPhotoListUseCase: GetDefaultMarsPhotoListUseCase,
    private val getNeededMatsPhotoListUseCase: GetNeededMatsPhotoListUseCase): ViewModel() {

    private val _marsRoverPhotos: MutableLiveData<List<MarsPhoto>?> = MutableLiveData()
    val marsRoverPhotos: LiveData<List<MarsPhoto>?> get() = _marsRoverPhotos
    private val _marsRoverTitle: MutableLiveData<String> = MutableLiveData()
    val marsRoverTitle: LiveData<String> get() = _marsRoverTitle

    init {
        getPhotoList()
        _marsRoverTitle.value = "Curiosity"
    }

    private fun getPhotoList() {
        viewModelScope.launch {
            val loadResult = getDefaultMarsPhotoListUseCase.execute("424", "Perseverance")
            when (loadResult) {
                is ApiResponse.Success -> {
                    _marsRoverPhotos.postValue(loadResult.data)
                }
                is ApiResponse.Error -> {
                    Log.e("App", "Error")
                }
            }
        }
    }

    fun getNeededMarsPhotoList(roverName: String) {
        viewModelScope.launch {
            val loadResult = getNeededMatsPhotoListUseCase.execute("2022-07-07", roverName = roverName)
            when(loadResult) {
                is ApiResponse.Success -> {
                    _marsRoverPhotos.postValue((loadResult.data))
                }
                is ApiResponse.Error -> {
                    Log.e("App", "Error")
                }
            }
        }
    }

    fun setMarsRoverTitle(title: String) {
        _marsRoverTitle.value = title
    }


}