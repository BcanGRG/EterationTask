package com.bcan.eterationtask.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bcan.eterationtask.data.domain.model.ProductResponseModel
import com.bcan.eterationtask.presentation.home.components.HomeTopAppBar
import com.bcan.eterationtask.presentation.home.components.ProductCard
import com.bcan.eterationtask.presentation.ui.animations.EmptyBasketAnimation
import com.bcan.eterationtask.presentation.ui.animations.LoadingAnimation

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (ProductResponseModel?) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = { BottomAppBar(modifier = Modifier.height(0.dp)) {} }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = Color.White
        ) {

            if (uiState.isLoading) LoadingAnimation()

            if (!uiState.isLoading && uiState.products.isNullOrEmpty()) EmptyBasketAnimation()

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                if (uiState.products.isNullOrEmpty().not()) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Filters:",
                        fontWeight = FontWeight(500),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(21.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        items(uiState.products!!) { product ->
                            ProductCard(
                                imageUrl = product?.image,
                                price = product?.price,
                                isFavorite = product?.isFavorite(uiState.favorites) ?: false,
                                productName = product?.name,
                                onClick = { navigateToDetail(product) },
                                onAddToCart = { viewModel.addProduct(product!!.toProductEntity()) },
                                onClickFavorite = { viewModel.addOrRemoveFavorite(product!!.toFavoriteProductEntity()) }
                            )
                        }
                    }
                }
            }
        }
    }
}
