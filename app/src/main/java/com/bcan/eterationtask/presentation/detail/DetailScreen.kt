package com.bcan.eterationtask.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bcan.eterationtask.data.model.ProductResponseModel
import com.bcan.eterationtask.presentation.detail.components.DetailTopAppBar

@Composable
fun DetailScreen(
    product: ProductResponseModel?,
    onBackClick: () -> Unit = {},
) {

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = product?.name,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

            }
        }
    }

}