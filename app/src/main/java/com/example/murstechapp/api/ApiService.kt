package com.example.murstechapp.api

import com.example.murstechapp.models.ItemModelApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/products")
    fun getData(
        @Query("limit")limit: Int
    ): Call<ItemModelApi>
}