package com.bcan.eterationtask.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bcan.eterationtask.data.model.ProductResponseModel
import com.bcan.eterationtask.presentation.detail.components.DetailTopAppBar
import com.bcan.eterationtask.ui.theme.Silver

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
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                AsyncImage(
                    model = product?.image,
                    contentDescription = "detail image url",
                    modifier = Modifier
                        .height(225.dp)
                        .fillMaxWidth()
                        .background(Silver),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = product?.name.orEmpty(),
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp,
                )
                Text(
                    text = product?.description.orEmpty(),
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp, lineHeight = 17.sp
                )
            }
        }
    }
}