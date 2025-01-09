package com.bcan.eterationtask.data.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponseModel(
    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("brand")
    val brand: String,

    @SerializedName("id")
    val id: String,

    val isFavorite: Boolean = false,
) {

    fun isFavorite(list: List<FavoriteProductEntity?>?): Boolean =
        list?.any { it?.id == id } ?: false

    fun toProductEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            price = price,
            quantity = 1,
        )
    }

    fun toFavoriteProductEntity(): FavoriteProductEntity {
        return FavoriteProductEntity(
            id = id,
            name = name,
            price = price,
            image = image
        )

    }
}