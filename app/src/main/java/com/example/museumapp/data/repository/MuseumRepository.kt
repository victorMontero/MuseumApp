package com.example.museumapp.data.repository

import com.example.museumapp.BuildConfig
import com.example.museumapp.data.mapper.toArtList
import com.example.museumapp.data.services.MuseumService
import com.example.museumapp.domain.model.ArtPiece
import com.example.museumapp.domain.repository.ArtRepository
import javax.inject.Inject

class MuseumRepository @Inject constructor(
    private val museumService: MuseumService
) : ArtRepository {
    private val apiKey = BuildConfig.API_KEY

    override suspend fun getArtObjects(): List<ArtPiece> {
        val response = museumService.getCollection(apiKey).toArtList()
        return response
    }
}