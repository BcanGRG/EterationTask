package com.bcan.eterationtask.presentation.home.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bcan.eterationtask.ui.theme.BlueRibbon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(title: String = "E-Market") {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(800)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = BlueRibbon),
    )
}