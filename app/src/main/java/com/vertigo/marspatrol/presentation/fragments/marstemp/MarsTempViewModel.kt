package com.vertigo.marspatrol.presentation.fragments.marstemp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.MarsTemp
import com.vertigo.marspatrol.domain.usecase.marstemp.GetMarsTempListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarsTempViewModel @Inject constructor(
    private val getMarsTempListUseCase: GetMarsTempListUseCase
): ViewModel() {

    private val _marsTempList: MutableLiveData<List<MarsTemp>?> = MutableLiveData()
    val marsTempList: LiveData<List<MarsTemp>?> get() = _marsTempList

    fun getMarsTempList() {
        viewModelScope.launch {
            val responseResult = getMarsTempListUseCase.execute()
            when (responseResult) {
                is ApiResponse.Success -> {
                    _marsTempList.postValue(responseResult.data)
                }
                is ApiResponse.Error -> {

                }
            }

        }
    }
}