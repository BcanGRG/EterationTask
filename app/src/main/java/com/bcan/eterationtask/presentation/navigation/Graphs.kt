package com.bcan.eterationtask.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.bcan.eterationtask.data.model.ProductResponseModel
import com.bcan.eterationtask.presentation.basket.BasketScreen
import com.bcan.eterationtask.presentation.detail.DetailScreen
import com.bcan.eterationtask.presentation.favorite.FavoriteScreen
import com.bcan.eterationtask.presentation.home.HomeScreen
import com.bcan.eterationtask.presentation.profile.ProfileScreen
import com.bcan.eterationtask.presentation.utils.CustomNavType
import kotlin.reflect.typeOf

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<Graphs.HomeGraph>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                navigateToDetail = { navController.navigate(DetailRoute(it)) }
            )
        }

        composable<DetailRoute>(
            typeMap = mapOf(typeOf<ProductResponseModel?>() to CustomNavType.ProductType),
        ) {
            val arguments = it.toRoute<DetailRoute>()
            DetailScreen(
                product = arguments.product,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

fun NavGraphBuilder.basketGraph() {
    navigation<Graphs.BasketGraph>(startDestination = BasketRoute) {
        composable<BasketRoute> {
            BasketScreen()
        }
    }
}

fun NavGraphBuilder.favoriteGraph() {
    navigation<Graphs.FavoriteGraph>(startDestination = FavoriteRoute) {
        composable<FavoriteRoute> {
            FavoriteScreen()
        }
    }
}

fun NavGraphBuilder.profileGraph() {
    navigation<Graphs.ProfileGraph>(startDestination = ProfileRoute) {
        composable<ProfileRoute> {
            ProfileScreen()
        }
    }
}