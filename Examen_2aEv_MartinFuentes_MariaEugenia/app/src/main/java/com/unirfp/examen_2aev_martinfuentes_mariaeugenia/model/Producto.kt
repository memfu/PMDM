package com.unirfp.examen_2aev_martinfuentes_mariaeugenia.model

data class Producto(
    val _id: String,
    val active: Boolean,
    val category: String,
    val description: String,
    val image: String,
    val name: String,
    val price: Double
)