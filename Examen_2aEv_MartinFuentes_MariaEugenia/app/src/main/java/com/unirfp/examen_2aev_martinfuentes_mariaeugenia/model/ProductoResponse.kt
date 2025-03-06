package com.unirfp.examen_2aev_martinfuentes_mariaeugenia.model

data class ProductoResponse(
    val page: Int,
    val per_page: Int,
    val results: List<Producto>,
    val total: Int,
    val total_pages: Int
)