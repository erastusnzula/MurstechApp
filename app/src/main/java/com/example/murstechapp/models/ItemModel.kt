package com.example.murstechapp.models

data class ItemModel(
    val name: String,
    val description: String,
    val price:Double,
    val quantity: Int,
    val discount: Int,
    val imageUrl: String,
    val size: Int,
    val rating: Double,
    val tag: String,
    val category: String
)