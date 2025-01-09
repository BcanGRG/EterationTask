package com.bcan.eterationtask.data.repository

import com.bcan.eterationtask.data.domain.model.ProductResponseModel
import com.bcan.eterationtask.data.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts(): Flow<NetworkResult<List<ProductResponseModel>>>
}