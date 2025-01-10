package com.bcan.eterationtask.data.domain.usecase

import com.bcan.eterationtask.data.local.ProductDao
import com.bcan.eterationtask.data.util.safeCall
import javax.inject.Inject

class IncreaseProductQuantityUseCase @Inject constructor(private val productDao: ProductDao) {
    suspend operator fun invoke(productId: String, quantity: Int) {
        safeCall { productDao.increaseProductQuantity(productId, quantity) }
    }
}