package com.bcan.eterationtask.data.repository

import com.bcan.eterationtask.data.local.ProductDao
import com.bcan.eterationtask.data.domain.model.ProductResponseModel
import com.bcan.eterationtask.data.domain.model.ProductResponseModelDao
import com.bcan.eterationtask.data.service.ProductsService
import com.bcan.eterationtask.data.util.NetworkResult
import com.bcan.eterationtask.data.util.sendRequestWithResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsService: ProductsService,
) : ProductsRepository {
    override suspend fun getProducts(): Flow<NetworkResult<List<ProductResponseModel>>> {
        return sendRequestWithResponse { productsService.getProducts() }
    }
}