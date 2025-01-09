package com.bcan.eterationtask.presentation.navigation

import com.bcan.eterationtask.data.domain.model.ProductResponseModel
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val product: ProductResponseModel?)

@Serializable
object BasketRoute

@Serializable
object FavoriteRoute

@Serializable
object ProfileRoute

@Serializable
sealed class Graphs {
    @Serializable
    data object HomeGraph : Graphs()

    @Serializable
    data object BasketGraph : Graphs()

    @Serializable
    data object FavoriteGraph : Graphs()

    @Serializable
    data object ProfileGraph : Graphs()
}

