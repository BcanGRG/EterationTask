package com.bcan.eterationtask.data.domain.usecase

import com.bcan.eterationtask.data.domain.model.ProductEntity
import com.bcan.eterationtask.data.local.ProductDao
import javax.inject.Inject

class AddProductUseCase @Inject constructor(private val productDao: ProductDao) {
    suspend operator fun invoke(product: ProductEntity) {
        val existingProduct = productDao.getProductById(product.id)
        if (existingProduct != null) {
            productDao.increaseProductQuantity(product.id, product.quantity)
        } else productDao.insertProduct(product)
    }
}