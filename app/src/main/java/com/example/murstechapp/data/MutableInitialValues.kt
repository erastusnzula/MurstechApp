package com.example.murstechapp.data

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap


object MutableInitialValues {
    val search: MutableState<String> = mutableStateOf("")
    val signUpError = mutableStateOf("")
    val signInError = mutableStateOf("")
    val profileImage = mutableStateOf<ImageBitmap?>(null)
    val currentUser = mutableStateOf("")
    val currentUserName = mutableStateOf("")
}