package com.bcan.eterationtask.data.domain.usecase

import com.bcan.eterationtask.data.local.ProductDao
import com.bcan.eterationtask.data.util.safeCall
import javax.inject.Inject

class DecreaseProductQuantityUseCase @Inject constructor(private val productDao: ProductDao) {
    suspend operator fun invoke(productId: String, quantity: Int) {
        safeCall {
            val currentProduct = productDao.getProductById(productId) ?: return@safeCall
            if (currentProduct.quantity <= quantity) {
                productDao.deleteProduct(currentProduct)
            } else {
                productDao.decreaseProductQuantity(productId, quantity)
            }
        }
    }
}