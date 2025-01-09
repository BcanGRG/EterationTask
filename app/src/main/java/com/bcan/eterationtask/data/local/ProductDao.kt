package com.bcan.eterationtask.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bcan.eterationtask.data.model.ProductResponseModelDao

@Dao
interface ProductDao {

    // Ürünü ekle veya miktarını artır
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: ProductResponseModelDao)

    @Delete
    suspend fun deleteProduct(product: ProductResponseModelDao)

    @Query("UPDATE product_table SET quantity = quantity + :quantity WHERE id = :productId")
    suspend fun increaseProductQuantity(productId: String, quantity: Int)

    @Query("UPDATE product_table SET quantity = quantity - :quantity WHERE id = :productId")
    suspend fun decreaseProductQuantity(productId: String, quantity: Int)

    @Query("SELECT * FROM product_table")
    suspend fun getAllProducts(): List<ProductResponseModelDao>

    // Ürünü kontrol et (var mı yok mu)
    @Query("SELECT * FROM product_table WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: String): ProductResponseModelDao?

}