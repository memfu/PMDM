package com.unirfp.ejercicio_retrofit_20250130

import com.unirfp.ejercicio_retrofit_20250130.model.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getAllSeries(@Url url: String): Response<SeriesResponse>

}