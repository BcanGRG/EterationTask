package com.bcan.eterationtask.data.service

import com.bcan.eterationtask.data.model.ProductResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductsService {

    @GET("products")
    suspend fun getProducts(): Response<List<ProductResponseModel>>

}