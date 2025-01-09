package com.bcan.eterationtask.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EterationTaskNavigation() {

    val navController = rememberNavController()
    val entry by navController.currentBackStackEntryAsState()
    val currentDestination = entry?.destination


    Scaffold(
        topBar = { TopAppBar(modifier = Modifier.height(0.dp), title = {}) },
        bottomBar = {
            BottomAppBar(containerColor = Color.White, contentPadding = PaddingValues(0.dp)) {
                bottomNavDestinations.forEach { destination ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.hasRoute(destination.route::class)
                        } ?: false,
                        onClick = { navController.navigate(destination.route) },
                        icon = {
                            Icon(
                                painter = painterResource(destination.icon),
                                contentDescription = "Icon"
                            )
                        },
                    )
                }
            }
        }) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Graphs.HomeGraph,
        ) {
            homeGraph(navController)
            basketGraph()
            favoriteGraph()
            profileGraph()
        }
    }
}