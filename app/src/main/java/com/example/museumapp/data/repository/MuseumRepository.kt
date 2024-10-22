package com.example.museumapp.data.repository

import com.example.museumapp.BuildConfig
import com.example.museumapp.data.model.ArtObject
import com.example.museumapp.data.services.MuseumService
import com.example.museumapp.utils.Constants
import javax.inject.Inject

class MuseumRepository @Inject constructor(
    private val museumService: MuseumService
) {
    private val apiKey = BuildConfig.API_KEY

    suspend fun getArtObjects(): List<ArtObject> {
        val response = museumService.getCollection(apiKey)
        return response.artObjects
    }
}