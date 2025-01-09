package com.bcan.eterationtask.data.domain.usecase

import com.bcan.eterationtask.data.local.ProductDao
import javax.inject.Inject

class DecreaseProductQuantityUseCase @Inject constructor(private val productDao: ProductDao) {
    suspend operator fun invoke(productId: String, quantity: Int) {
        val currentProduct = productDao.getProductById(productId) ?: return
        if (currentProduct.quantity <= quantity) {
            productDao.deleteProduct(currentProduct)
        } else {
            productDao.decreaseProductQuantity(productId, quantity)
        }
    }
}