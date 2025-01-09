package com.bcan.eterationtask.data.repository

import com.bcan.eterationtask.data.domain.model.ProductResponseModel
import com.bcan.eterationtask.data.service.ProductsService
import com.bcan.eterationtask.data.util.NetworkResult
import com.bcan.eterationtask.data.util.sendRequestWithResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsService: ProductsService,
) : ProductsRepository {
    override suspend fun getProducts(): Flow<NetworkResult<List<ProductResponseModel>>> {
        return sendRequestWithResponse { productsService.getProducts() }
    }
}