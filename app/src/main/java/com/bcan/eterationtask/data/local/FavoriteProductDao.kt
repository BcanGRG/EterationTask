package com.bcan.eterationtask.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bcan.eterationtask.data.domain.model.FavoriteProductEntity

@Dao
interface FavoriteProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteProduct(product: FavoriteProductEntity)

    @Delete
    suspend fun removeFavoriteProduct(product: FavoriteProductEntity)

    @Query("SELECT * FROM favorite_table")
    suspend fun getAllFavoriteProducts(): List<FavoriteProductEntity>

    @Query("SELECT * FROM favorite_table WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: String): FavoriteProductEntity?
}