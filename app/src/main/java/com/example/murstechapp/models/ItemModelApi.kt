package com.example.murstechapp.models

import android.health.connect.datatypes.units.Percentage
import androidx.annotation.Dimension

data class ItemModelApi(
    val products: List<Products>
)

data class Products(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val sku: String,
    val weight: Int,
    val images: List<String>,
    val thumbnail: String
)





