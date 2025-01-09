package com.bcan.eterationtask.data.repository

import com.bcan.eterationtask.data.model.ProductResponseModel
import com.bcan.eterationtask.data.service.ProductsService
import com.bcan.eterationtask.data.util.NetworkResult
import com.bcan.eterationtask.data.util.sendRequestWithResponse
import kotlinx.coroutines.flow.Flow

class ProductsRepositoryImpl(
    private val productsService: ProductsService
) : ProductsRepository {
    override suspend fun getProducts(): Flow<NetworkResult<List<ProductResponseModel>>> {
        return sendRequestWithResponse { productsService.getProducts() }
    }
}