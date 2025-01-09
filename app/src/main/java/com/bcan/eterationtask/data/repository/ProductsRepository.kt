package com.bcan.eterationtask.data.repository

import com.bcan.eterationtask.data.model.ProductResponseModel
import com.bcan.eterationtask.data.model.ProductResponseModelDao
import com.bcan.eterationtask.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts(): Flow<NetworkResult<List<ProductResponseModel>>>
    suspend fun addProduct(product: ProductResponseModelDao)
    suspend fun deleteProduct(product: ProductResponseModelDao)
    suspend fun getAllProducts(): List<ProductResponseModelDao>
}