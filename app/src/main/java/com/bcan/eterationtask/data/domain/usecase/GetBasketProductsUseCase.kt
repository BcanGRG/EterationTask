package com.bcan.eterationtask.data.domain.usecase

import com.bcan.eterationtask.data.domain.model.ProductResponseModelDao
import com.bcan.eterationtask.data.local.ProductDao
import javax.inject.Inject

class GetBasketProductsUseCase @Inject constructor(
    private val productDao: ProductDao,
) {
    suspend operator fun invoke(): List<ProductResponseModelDao> {
        return productDao.getAllProducts()
    }
}