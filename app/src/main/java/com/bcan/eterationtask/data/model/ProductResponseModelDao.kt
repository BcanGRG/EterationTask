package com.bcan.eterationtask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductResponseModelDao(
    @PrimaryKey val id: String,
    val name: String,
    val price: String,
    val quantity: Int = 1
)