package com.bcan.eterationtask.data.domain.usecase

import com.bcan.eterationtask.data.domain.model.FavoriteProductEntity
import com.bcan.eterationtask.data.local.FavoriteProductDao
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoriteProductDao: FavoriteProductDao,
) {
    suspend operator fun invoke(): List<FavoriteProductEntity> {
        return favoriteProductDao.getAllFavoriteProducts()
    }
}