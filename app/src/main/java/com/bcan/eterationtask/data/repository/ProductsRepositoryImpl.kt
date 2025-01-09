package com.bcan.eterationtask.data.repository

import com.bcan.eterationtask.data.local.ProductDao
import com.bcan.eterationtask.data.model.ProductResponseModel
import com.bcan.eterationtask.data.model.ProductResponseModelDao
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
    private val productDao: ProductDao,
) : ProductsRepository {
    override suspend fun getProducts(): Flow<NetworkResult<List<ProductResponseModel>>> {
        return sendRequestWithResponse { productsService.getProducts() }
    }

    override suspend fun addProduct(product: ProductResponseModelDao) {
        val existingProduct = productDao.getProductById(product.id)
        if (existingProduct != null) {
            productDao.increaseProductQuantity(product.id, product.quantity)
        } else productDao.insertProduct(product)
    }

    override suspend fun deleteProduct(product: ProductResponseModelDao) {
        productDao.deleteProduct(product)
    }

    override suspend fun increaseQuantity(productId: String, amount: Int) {
        productDao.increaseProductQuantity(productId, amount)
    }

    override suspend fun decreaseQuantity(productId: String, amount: Int) {
        val currentProduct = productDao.getProductById(productId) ?: return
        if (currentProduct.quantity <= amount) {
            productDao.deleteProduct(currentProduct)
        } else {
            productDao.decreaseProductQuantity(productId, amount)
        }
    }

    override suspend fun getBasketProducts(): Flow<List<ProductResponseModelDao>> {
        return flow {
            emit(productDao.getAllProducts())
        }.flowOn(Dispatchers.IO)
    }


}