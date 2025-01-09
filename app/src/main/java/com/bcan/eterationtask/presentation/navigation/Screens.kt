package com.bcan.eterationtask.presentation.navigation

import com.bcan.eterationtask.data.model.ProductResponseModel
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val product: ProductResponseModel?)

@Serializable
object BasketRoute

@Serializable
object FavoriteRoute