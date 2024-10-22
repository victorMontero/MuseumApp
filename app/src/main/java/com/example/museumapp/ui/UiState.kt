package com.example.museumapp.ui

import com.example.museumapp.data.model.ArtObject

sealed class UiState{
    object Loading: UiState()
    data class Success(val result : List<ArtObject>) : UiState()
    data class Error(val message: String) : UiState()
}
