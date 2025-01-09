package com.bcan.eterationtask.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bcan.eterationtask.data.model.ProductResponseModel
import com.bcan.eterationtask.presentation.home.components.DetailTopAppBar

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
    ) {
        Column(modifier = Modifier.padding(it)) {

        }
    }

}