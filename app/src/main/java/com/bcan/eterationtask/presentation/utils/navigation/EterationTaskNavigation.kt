package com.bcan.eterationtask.presentation.utils.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bcan.eterationtask.presentation.ui.snackbar.ObserveAsEvents
import com.bcan.eterationtask.presentation.ui.snackbar.SnackbarController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EterationTaskNavigation() {

    val navController = rememberNavController()
    val entry by navController.currentBackStackEntryAsState()
    val currentDestination = entry?.destination

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    ObserveAsEvents(flow = SnackbarController.events, snackbarHostState) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Short,
            )

            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }

    }

    Scaffold(
        topBar = { TopAppBar(modifier = Modifier.height(0.dp), title = {}) },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                modifier = Modifier.height(80.dp)
            ) {
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
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
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