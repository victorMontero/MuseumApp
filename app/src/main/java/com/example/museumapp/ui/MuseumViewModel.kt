package com.example.museumapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.data.model.ArtObject
import com.example.museumapp.data.repository.MuseumRepository
import kotlinx.coroutines.launch

class MuseumViewModel: ViewModel() {
    private val repository = MuseumRepository()

    private val _artObjects = MutableLiveData<List<ArtObject>>()
    val artObjects: LiveData<List<ArtObject>> = _artObjects

    fun getArtObjects() {
        viewModelScope.launch {
            try {
                val arts = repository.getArtObjects()
                _artObjects.value = arts
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}