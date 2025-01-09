package com.bcan.eterationtask.presentation.navigation

import com.bcan.eterationtask.R
import kotlinx.serialization.Serializable

val bottomNavDestinations =
    listOf(
        BottomNavItem(Graphs.HomeGraph, R.drawable.ic_home),
        BottomNavItem(Graphs.BasketGraph, R.drawable.ic_basket),
        BottomNavItem(Graphs.FavoriteGraph, R.drawable.ic_favorite_bottom),
        BottomNavItem(Graphs.ProfileGraph, R.drawable.ic_profile),
    )


@Serializable
data class BottomNavItem(
    val route: Graphs,
    val icon: Int,
)