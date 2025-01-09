package com.bcan.eterationtask.data.domain.usecase

import com.bcan.eterationtask.data.local.ProductDao
import javax.inject.Inject

class IncreaseProductQuantityUseCase @Inject constructor(private val productDao: ProductDao) {
    suspend operator fun invoke(productId: String, quantity: Int) {
        productDao.increaseProductQuantity(productId, quantity)
    }
}