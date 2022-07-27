package com.vertigo.marspatrol.presentation.fragments.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.usecase.marsphoto.GetFavoriteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteListUseCase: GetFavoriteListUseCase
): ViewModel() {
    private val _favoritePhotosViewModel: MutableLiveData<List<MarsPhoto>> = MutableLiveData()
    val favoritePhotosViewModel: LiveData<List<MarsPhoto>> get() = _favoritePhotosViewModel

    fun getFavoritePhotosList() {
        viewModelScope.launch {
            _favoritePhotosViewModel.postValue(getFavoriteListUseCase.execute())
        }
    }
}