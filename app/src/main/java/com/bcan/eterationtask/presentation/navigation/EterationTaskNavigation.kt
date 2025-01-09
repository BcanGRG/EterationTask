package com.bcan.eterationtask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bcan.eterationtask.data.model.ProductResponseModel
import com.bcan.eterationtask.presentation.detail.DetailScreen
import com.bcan.eterationtask.presentation.home.HomeScreen
import com.bcan.eterationtask.presentation.utils.CustomNavType
import kotlin.reflect.typeOf

@Composable
fun EterationTaskNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute,
    ) {
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