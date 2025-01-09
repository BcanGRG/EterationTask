package com.bcan.eterationtask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bcan.eterationtask.data.domain.model.ProductResponseModelDao

@Database(entities = [ProductResponseModelDao::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}