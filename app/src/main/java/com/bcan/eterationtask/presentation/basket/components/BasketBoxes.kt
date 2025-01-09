package com.bcan.eterationtask.presentation.basket.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcan.eterationtask.ui.theme.BlueRibbon

@Composable
fun RowScope.IncreaseDecreaseBox(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick() }
            .background(color = Color.LightGray.copy(0.5f), shape = RoundedCornerShape(4.dp))
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .align(Alignment.CenterVertically),
        fontWeight = FontWeight(700),
        fontSize = 16.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RowScope.QuantityBox(
    text: String,
) {
    Text(
        text = text,
        modifier = Modifier
            .background(color = BlueRibbon, shape = RectangleShape)
            .padding(vertical = 10.dp, horizontal = 23.dp)
            .align(Alignment.CenterVertically),
        fontWeight = FontWeight(400),
        fontSize = 18.sp,
        lineHeight = 22.sp,
        textAlign = TextAlign.Center, color = Color.White
    )
}