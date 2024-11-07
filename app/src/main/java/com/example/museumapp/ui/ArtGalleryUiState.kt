package com.example.museumapp.ui

import com.example.museumapp.domain.model.ArtPiece

sealed class ArtGalleryUiState {
    object Loading : ArtGalleryUiState()
    data class Success(val result: List<ArtPiece>) : ArtGalleryUiState()
    data class Error(val message: String) : ArtGalleryUiState()
}
