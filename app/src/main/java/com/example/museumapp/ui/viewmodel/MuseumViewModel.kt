package com.example.museumapp.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.museumapp.data.repository.MuseumRepository
import com.example.museumapp.domain.model.ArtPiece
import com.example.museumapp.ui.ArtGalleryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MuseumViewModel @Inject constructor(
    private val repository: MuseumRepository,
) : BaseViewModel<ArtGalleryUiState>() {

    private var artObjects: List<ArtPiece> = emptyList()

    fun getArtObjects() {
        viewModelScope.launch {
            try {
                artObjects = repository.getArtObjects()
                uiState.value = ArtGalleryUiState.Success(artObjects)
            } catch (e: Exception) {
                uiState.value = ArtGalleryUiState.Error(e.message ?: "Unknown Erro")
            }
        }
    }

    fun getArtObjectById(id: String?): ArtPiece? {
        return artObjects.find { it.id == id }
    }
}