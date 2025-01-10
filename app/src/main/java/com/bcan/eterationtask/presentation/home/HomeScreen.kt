package com.bcan.eterationtask.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bcan.eterationtask.R
import com.bcan.eterationtask.data.domain.model.ProductResponseModel
import com.bcan.eterationtask.presentation.home.components.HomeTopAppBar
import com.bcan.eterationtask.presentation.home.components.ProductCard
import com.bcan.eterationtask.presentation.ui.animations.EmptyBasketAnimation
import com.bcan.eterationtask.presentation.ui.animations.LoadingAnimation
import com.bcan.eterationtask.presentation.ui.snackbar.SnackbarController
import com.bcan.eterationtask.presentation.ui.snackbar.SnackbarEvent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (ProductResponseModel?) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val products by viewModel.products.collectAsStateWithLifecycle()


    uiState.errorMessage?.let { message ->
        LaunchedEffect(message) {
            SnackbarController.sendEvent(
                event = SnackbarEvent(message)
            )
        }
    }

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

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                Spacer(modifier = Modifier.height(14.dp))
                Surface(color = Color.LightGray.copy(0.5f), shape = RoundedCornerShape(8.dp)) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = null, tint = Color.Unspecified
                        )
                        BasicTextField(
                            value = searchText,
                            onValueChange = viewModel::onSearchTextChange,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp),
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight(400),
                                lineHeight = 22.sp
                            ),
                            decorationBox = { innerTextField ->
                                if (searchText.isEmpty()) {
                                    Text(
                                        text = "Search",
                                        fontWeight = FontWeight(500),
                                        fontSize = 18.sp,
                                        lineHeight = 22.sp, color = Color.Gray
                                    )
                                }
                                innerTextField()
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                if (uiState.isLoading) LoadingAnimation()
                else if (products.isNullOrEmpty()) EmptyBasketAnimation()
                else {
                    if (products.isNullOrEmpty().not()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(21.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp),
                        ) {
                            items(products!!) { product ->
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
}
