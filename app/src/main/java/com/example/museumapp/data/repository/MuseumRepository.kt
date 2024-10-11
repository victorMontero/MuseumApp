package com.example.museumapp.data.repository

import com.example.museumapp.BuildConfig
import com.example.museumapp.data.model.ArtObject
import com.example.museumapp.data.services.RetrofitInstance
import com.example.museumapp.utils.Constants

class MuseumRepository {
    private val museumService = RetrofitInstance.museumService
    private val apiKey = BuildConfig.API_KEY

    suspend fun getArtObjects(): List<ArtObject> {
        val response = museumService.getCollection(apiKey)
        return response.artObjects
    }
}