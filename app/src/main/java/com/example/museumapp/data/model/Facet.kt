package com.example.museumapp.data.model

data class Facet(
    val facets: List<FacetX>,
    val name: String,
    val otherTerms: Int,
    val prettyName: Int
)