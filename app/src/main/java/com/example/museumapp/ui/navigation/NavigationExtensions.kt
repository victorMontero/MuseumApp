package com.example.museumapp.ui.navigation

fun MuseumDestinations.createRoute(vararg arguments: Pair<String, String>): String {
    var finalRoute = this.route
    arguments.forEach { (placeholder, value) ->
        finalRoute = finalRoute.replace("{$placeholder}", value)
    }
    return finalRoute
}