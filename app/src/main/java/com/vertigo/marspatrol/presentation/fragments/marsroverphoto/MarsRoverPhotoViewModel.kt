package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsDatePicker
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetDefaultSolForMarsRoverUseCase
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetNeededMatsPhotoListUseCase
import com.vertigo.marspatrol.presentation.App
import kotlinx.coroutines.launch

class MarsRoverPhotoViewModel(
    private val getNeededMatsPhotoListUseCase: GetNeededMatsPhotoListUseCase,
    private val getDefaultSolForMarsRoverUseCase: GetDefaultSolForMarsRoverUseCase): ViewModel() {

    private val _marsRoverPhotos: MutableLiveData<List<MarsPhoto>?> = MutableLiveData()
    val marsRoverPhotos: LiveData<List<MarsPhoto>?> get() = _marsRoverPhotos
    private val _marsRoverTitle: MutableLiveData<String> = MutableLiveData()
    val marsRoverTitle: LiveData<String> get() = _marsRoverTitle
    private val _marsRoverDate: MutableLiveData<MarsDatePicker> = MutableLiveData()
    val marsRoverDate: LiveData<MarsDatePicker> get() = _marsRoverDate
    private val _errorConnectionMessage: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorConnectionMessage: LiveData<Boolean> get() = _errorConnectionMessage
    private val _emptyListMessage: MutableLiveData<Boolean> = MutableLiveData(false)
    val emptyListMessage: LiveData<Boolean> get() = _emptyListMessage

    init {
        _marsRoverTitle.value = App.NAME_CURIOSITY
        getDefaultMarsRoverSol()
    }

    private fun getDefaultMarsRoverSol() {
        viewModelScope.launch {
            val loadResult = _marsRoverTitle.value?.let {
                getDefaultSolForMarsRoverUseCase.execute(
                    it
                )
            }
            when (loadResult) {
                is ApiResponse.Success -> {
                    loadResult.data?.max_date?.let {
                        _marsRoverDate.postValue(convertStringDataToObject(it)) }
                }
                is ApiResponse.Error -> {
                    _errorConnectionMessage.value = true
                }
            }
        }
    }

    fun getNeededMarsPhotoList() {
        val neededDate = _marsRoverDate.value
        viewModelScope.launch {
            val loadResult = _marsRoverTitle.value?.let {
                getNeededMatsPhotoListUseCase.execute(
                    "${neededDate?.year}-${neededDate?.month}-${neededDate?.day}", roverName = it
                )
            }
            when(loadResult) {
                is ApiResponse.Success -> {
                    _marsRoverPhotos.postValue(loadResult.data)
                    if (loadResult.data.isEmpty()) {
                        _emptyListMessage.value = true
                    }
                }
                is ApiResponse.Error -> {
                    _errorConnectionMessage.value = true
                }
            }
        }
    }

    fun setMarsRoverTitle(title: String) {
        _marsRoverTitle.value = title
    }

    fun setDateForCalendar(day: Int, month: Int, year: Int) {
        _marsRoverDate.value = MarsDatePicker(day.toString(), (month + 1).toString(), year.toString())
    }

    private fun convertStringDataToObject(date: String): MarsDatePicker {
        val year = date.substring(0, 4)
        val month = date.substring(5, 7)
        val day = date.substring(8, 10)

        return MarsDatePicker(day, month, year)
    }
}