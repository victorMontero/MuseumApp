package com.example.museumapp.data.services

import com.example.museumapp.data.model.MuseumResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MuseumService {
    @GET("collection")
    suspend fun getCollection(
        @Query("key") apiKey: String,
        @Query("toppieces") isTopPieces: Boolean = true,
        @Query("imgonly") imgOnly: Boolean = true,
        @Query("ps") resultsPerPage: Int = 100,
        @Query("involvedMaker") involvedMaker: String = "Vincent van Gogh"
    ): MuseumResponse
}