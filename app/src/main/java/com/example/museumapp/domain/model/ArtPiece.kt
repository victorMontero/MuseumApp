package com.example.museumapp.domain.model


data class ArtPiece(
    val id: String,
    val title: String,
    val longTitle: String,
    val objectNumber: String,
    val image: ArtImage
)
