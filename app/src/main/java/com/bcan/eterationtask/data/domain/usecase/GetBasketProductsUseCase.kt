package com.bcan.eterationtask.data.domain.usecase

import com.bcan.eterationtask.data.domain.model.ProductEntity
import com.bcan.eterationtask.data.local.ProductDao
import com.bcan.eterationtask.data.util.safeCall
import javax.inject.Inject

class GetBasketProductsUseCase @Inject constructor(
    private val productDao: ProductDao,
) {
    suspend operator fun invoke(): List<ProductEntity> {
        return safeCall { productDao.getAllProducts()}
    }
}