package com.example.murstechapp.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import com.example.murstechapp.models.Products


object MutableInitialValues {

    val search: MutableState<String> = mutableStateOf("")
    val signUpError = mutableStateOf("")
    val signInError = mutableStateOf("")
    val profileImage = mutableStateOf<ImageBitmap?>(null)
    val currentUser = mutableStateOf("")
    val currentUserName = mutableStateOf("")
    val error= mutableStateOf("")
    val featured= mutableStateOf("")
    val seeAll= mutableStateOf("")
    val mostPopular= mutableStateOf("")
    val electronics= mutableStateOf("")
    val carouselTitle = mutableStateOf("")
    val carouselDiscount = mutableStateOf("")
    val allItemsApi = mutableStateOf(ArrayList<Products>())
    val allItemsApiCarousel = mutableStateOf(ArrayList<Products>())
    val allItemsApiMostPopular = mutableStateOf(ArrayList<Products>())
    val allItemsApiElectronics = mutableStateOf(ArrayList<Products>())
}