package com.bcan.eterationtask.presentation.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bcan.eterationtask.presentation.basket.components.BasketProductCard
import com.bcan.eterationtask.presentation.home.components.HomeTopAppBar
import com.bcan.eterationtask.presentation.ui.PriceAndButtonComponent

@Composable
fun BasketScreen(
    viewModel: BasketViewModel = hiltViewModel()
) {

    val products by viewModel.products.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

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
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                LazyColumn(
                    modifier = Modifier.padding(vertical = 25.dp, horizontal = 25.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(products) { product ->
                        BasketProductCard(
                            name = product.name,
                            price = product.price,
                            quantity = product.quantity,
                            onIncrease = { viewModel.increaseQuantity(product.id) },
                            onDecrease = { viewModel.decreaseQuantity(product.id) }
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp, vertical = 20.dp)
                ) {
                    PriceAndButtonComponent(
                        price = totalPrice.toString(),
                        buttonText = "Complete"
                    )
                }
            }
        }
    }
}