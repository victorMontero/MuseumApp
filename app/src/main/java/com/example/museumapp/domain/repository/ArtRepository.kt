package com.example.museumapp.domain.repository

import com.example.museumapp.domain.model.ArtPiece

interface ArtRepository {
    suspend fun getArtObjects(): List<ArtPiece>
}