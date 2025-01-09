package com.bcan.eterationtask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcan.eterationtask.presentation.home.HomeScreen

@Composable
fun EterationTaskNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        composable<Home> {
            HomeScreen()
        }
    }
}