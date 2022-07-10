package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsDatePicker
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetDefaultMarsPhotoListUseCase
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetNeededMatsPhotoListUseCase
import com.vertigo.marspatrol.presentation.App
import kotlinx.coroutines.launch
import java.util.*

class MarsRoverPhotoViewModel(
    private val getDefaultMarsPhotoListUseCase: GetDefaultMarsPhotoListUseCase,
    private val getNeededMatsPhotoListUseCase: GetNeededMatsPhotoListUseCase): ViewModel() {

    private val _marsRoverPhotos: MutableLiveData<List<MarsPhoto>?> = MutableLiveData()
    val marsRoverPhotos: LiveData<List<MarsPhoto>?> get() = _marsRoverPhotos
    private val _marsRoverTitle: MutableLiveData<String> = MutableLiveData()
    val marsRoverTitle: LiveData<String> get() = _marsRoverTitle
    private val _marsRoverDate: MutableLiveData<MarsDatePicker> = MutableLiveData()
    val marsRoverDate: LiveData<MarsDatePicker> get() = _marsRoverDate

    init {
        _marsRoverTitle.value = App.NAME_CURIOSITY
        getDefaultPhotoList()
        initDateForCalendar()
    }

    private fun getDefaultPhotoList() {
        viewModelScope.launch {
            val loadResult =
                _marsRoverTitle.value?.let { getDefaultMarsPhotoListUseCase.execute("424", it) }
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

    private fun initDateForCalendar() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        _marsRoverDate.value = MarsDatePicker(day, month + 1, year)
    }

    fun setDateForCalendar(day: Int, month: Int, year: Int) {
        _marsRoverDate.value = MarsDatePicker(day, month + 1, year)
    }
}