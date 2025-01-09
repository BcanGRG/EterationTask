package com.bcan.eterationtask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bcan.eterationtask.data.domain.model.FavoriteProductEntity
import com.bcan.eterationtask.data.domain.model.ProductEntity

@Database(
    entities = [ProductEntity::class, FavoriteProductEntity::class],
    version = 2,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun favoriteProductDao(): FavoriteProductDao
}