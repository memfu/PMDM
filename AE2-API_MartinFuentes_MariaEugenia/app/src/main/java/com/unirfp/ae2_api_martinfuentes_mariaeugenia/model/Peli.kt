package com.unirfp.ae2_api_martinfuentes_mariaeugenia.model

import com.google.gson.annotations.SerializedName

data class Peli(
    @SerializedName("Actors") val actores: String,
    @SerializedName("Awards") val premios: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    @SerializedName("Genre") val genero: String,
    @SerializedName("Language") val idioma: String,
    val Metascore: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Poster") val poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    @SerializedName("Title") val titulo: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    @SerializedName("Year") val anio: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
)