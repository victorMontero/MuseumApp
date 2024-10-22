package com.example.museumapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.museumapp.data.model.ArtObject
import com.example.museumapp.data.repository.MuseumRepository
import com.example.museumapp.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MuseumViewModel @Inject constructor(
    private val repository: MuseumRepository,
) : BaseViewModel<UiState>() {

    private var artObjects: List<ArtObject> = emptyList()

    fun getArtObjects() {
        viewModelScope.launch {
            try {
                artObjects = repository.getArtObjects()
                uiState.value = UiState.Success(artObjects)
            } catch (e: Exception){
                uiState.value = UiState.Error(e.message ?: "Unknown Erro")
            }
        }
    }

    fun getArtObjectById(id: String?): ArtObject? {
        return artObjects.find { it.id == id }
    }
}