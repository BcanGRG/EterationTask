package com.bcan.eterationtask.presentation.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bcan.eterationtask.presentation.home.components.HomeTopAppBar
import com.bcan.eterationtask.presentation.ui.animations.EmptyBasketAnimation
import com.bcan.eterationtask.ui.theme.BlueRibbon

@Composable
fun FavoriteScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    val favorites by viewModel.favorites.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { HomeTopAppBar(title = "Favorites") },
        bottomBar = { BottomAppBar(modifier = Modifier.height(0.dp)) {} }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = Color.White
        ) {
            if (favorites.isNullOrEmpty()) EmptyBasketAnimation()
            else {
                LazyColumn {
                    items(favorites!!) {
                        ListItem(
                            headlineContent = { Text(text = it?.name ?: "") },
                            supportingContent = { Text(text = "${it?.price} ₺") },
                            leadingContent = {
                                AsyncImage(
                                    model = it?.image,
                                    contentDescription = "Product Image",
                                    modifier = Modifier.size(56.dp)
                                )
                            },
                            trailingContent = {
                                Icon(
                                    modifier = Modifier.clickable { viewModel.addOrRemoveFavorite(it!!) },
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Favorite", tint = Color.Red
                                )
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = Color.White,
                                supportingColor = BlueRibbon
                            )
                        )
                    }
                }
            }
        }
    }
}