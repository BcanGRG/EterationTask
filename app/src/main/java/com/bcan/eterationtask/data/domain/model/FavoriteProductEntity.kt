package com.bcan.eterationtask.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val price: String,
    val image: String
)
