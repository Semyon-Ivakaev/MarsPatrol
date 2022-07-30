package com.vertigo.marspatrol.presentation.fragments.marstemp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vertigo.marspatrol.domain.model.MarsTemp
import com.vertigo.marspatrol.domain.usecase.marstemp.GetMarsTempListUseCase
import javax.inject.Inject

class MarsTempViewModel @Inject constructor(
    private val getMarsTempListUseCase: GetMarsTempListUseCase
): ViewModel() {

    private val _marsTempList: MutableLiveData<List<MarsTemp>> = MutableLiveData()
    val marsTempList: LiveData<List<MarsTemp>> get() = _marsTempList


}