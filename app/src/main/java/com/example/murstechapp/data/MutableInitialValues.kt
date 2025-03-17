package com.example.murstechapp.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


object MutableInitialValues {
    val search: MutableState<String> = mutableStateOf("")
}