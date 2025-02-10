package com.unirfp.ae2_api_martinfuentes_mariaeugenia

import com.unirfp.ae2_api_martinfuentes_mariaeugenia.model.Peli
import com.unirfp.ae2_api_martinfuentes_mariaeugenia.model.PeliResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun getMovieByTitle( // suspend indica que es una función asíncrona
        @Query("apikey") apiKey: String,
        @Query("t") title: String
    ): Response<Peli>

    @GET("/")
    suspend fun getMoviesBySearch(
        @Query("apikey") apiKey: String,
        @Query("s") search: String
    ): Response<PeliResponse>
}