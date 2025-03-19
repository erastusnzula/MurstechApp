package com.example.murstechapp.models

import androidx.compose.ui.graphics.painter.Painter

data class ItemModel(
    val title: String,
    val description: String,
    val price:Double,
    val quantity: Int,
    val discount: Int,
    val imageUrl: Painter,
    val size: Int,
    val rating: Double,
    val tag: String,
    val category: String
)