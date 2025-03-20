package com.example.murstechapp.api

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.murstechapp.data.MutableInitialValues
import com.example.murstechapp.models.ItemModelApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ApiCall() {
    val context = LocalContext.current
    val call = ApiClient.apiService.getData(100)
    call.enqueue(object: Callback<ItemModelApi> {
        override fun onResponse(p0: Call<ItemModelApi>, p1: Response<ItemModelApi>) {
            if(p1.isSuccessful){
                MutableInitialValues.carouselTitle.value = "Get Winter Discount"
                MutableInitialValues.featured.value = "Featured"
                MutableInitialValues.electronics.value = "Electronics"
                MutableInitialValues.mostPopular.value = "Most Popular"
                MutableInitialValues.seeAll.value = "See All"
                Toast.makeText(context, "Data fetch complete!", Toast.LENGTH_LONG).show()
                MutableInitialValues.carouselDiscount.value = "% Off"
                MutableInitialValues.allItemsApi.value.clear()
                MutableInitialValues.allItemsApiCarousel.value.clear()
                MutableInitialValues.allItemsApiMostPopular.value.clear()
                MutableInitialValues.allItemsApiElectronics.value.clear()
                for (product in p1.body()?.products!!){
                    MutableInitialValues.allItemsApi.value.add(product)

                }
                for (product in p1.body()!!.products.takeLast(7)){
                    MutableInitialValues.allItemsApiCarousel.value.add(product)

                }

                for (product in p1.body()!!.products.takeLast(50)){
                    MutableInitialValues.allItemsApiMostPopular.value.add(product)

                }

                for (product in p1.body()!!.products){
                    if (product.category.contains("laptops")||product.category.contains("tablets")){
                        MutableInitialValues.allItemsApiElectronics.value.add(product)
                    }


                }




            }else{
                MutableInitialValues.error.value = p1.message().toString()
            }
        }

        override fun onFailure(p0: Call<ItemModelApi>, p1: Throwable) {
            MutableInitialValues.error.value = p1.message.toString()

        }

    })
}