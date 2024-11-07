package com.example.museumapp.data.mapper

import com.example.museumapp.data.model.dto.ArtObjectDTO
import com.example.museumapp.data.model.dto.MuseumResponseDTO
import com.example.museumapp.data.model.dto.WebImageDTO
import com.example.museumapp.domain.model.ArtImage
import com.example.museumapp.domain.model.ArtPiece

fun MuseumResponseDTO.toArtList(): List<ArtPiece> {
    return artObjects.map {
        it.toArtPiece()
    }
}

fun ArtObjectDTO.toArtPiece(): ArtPiece {
    return ArtPiece(
        id = this.id,
        title = this.title,
        longTitle = this.longTitle,
        objectNumber = this.objectNumber,
        image = this.webImage.toArtImage(),
    )
}

fun WebImageDTO.toArtImage(): ArtImage {
    return ArtImage(
        url = this.url
    )
}
