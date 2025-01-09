package com.bcan.eterationtask.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcan.eterationtask.presentation.home.components.HomeTopAppBar
import com.bcan.eterationtask.presentation.home.components.ProductCard

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { HomeTopAppBar() },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
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
                    items(10) {
                        ProductCard(
                            imageUrl = "",
                            price = "1500",
                            isFavorite = true,
                            productName = "iPhone 13 Pro Max 256Gb"
                        )
                    }
                }
            }
        }

    }
}
