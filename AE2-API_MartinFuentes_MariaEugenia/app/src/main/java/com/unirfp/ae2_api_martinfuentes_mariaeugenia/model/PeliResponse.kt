package com.unirfp.ae2_api_martinfuentes_mariaeugenia.model

import com.google.gson.annotations.SerializedName

data class PeliResponse(
    @SerializedName("Search") val busqueda: List<PeliSummary>?
)

data class PeliSummary(
    @SerializedName("Title") val titulo: String,
    @SerializedName("Year") val anio: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("imdbRating") val imdbRating: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Actors") val actores: String
)
