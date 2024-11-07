package com.example.museumapp.ui.navigation

sealed class MuseumDestinations(val route: String) {
    object MuseumScreen : MuseumDestinations("museu")
    object ArtDetailScreen : MuseumDestinations("artDetail/{artObjectId}") {
        const val artObjectIdArg = "artObjectId"
    }
}


