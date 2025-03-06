package com.unirfp.examen_2aev_martinfuentes_mariaeugenia

import com.unirfp.examen_2aev_martinfuentes_mariaeugenia.model.ProductoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getAllProducts(@Url url: String): Response<ProductoResponse>
}