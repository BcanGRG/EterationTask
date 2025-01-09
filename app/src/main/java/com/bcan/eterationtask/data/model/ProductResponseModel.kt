package com.bcan.eterationtask.data.model

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
    val id: String
)