package com.bcan.eterationtask.data.domain.usecase

import com.bcan.eterationtask.data.domain.model.FavoriteProductEntity
import com.bcan.eterationtask.data.local.FavoriteProductDao
import com.bcan.eterationtask.data.util.safeCall
import javax.inject.Inject

class AddOrRemoveFavoriteUseCase @Inject constructor(private val favoriteProductDao: FavoriteProductDao) {
    suspend operator fun invoke(product: FavoriteProductEntity) {
        safeCall {
            val existingProduct = favoriteProductDao.getProductById(product.id)
            if (existingProduct != null) {
                favoriteProductDao.removeFavoriteProduct(product)
            } else favoriteProductDao.addFavoriteProduct(product)
        }
    }
}