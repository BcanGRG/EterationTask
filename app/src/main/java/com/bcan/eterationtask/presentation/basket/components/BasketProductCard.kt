package com.bcan.eterationtask.presentation.basket.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bcan.eterationtask.ui.theme.BlueRibbon

@Composable
fun BasketProductCard(
    name: String,
    price: String,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row {
        Column {
            Text(
                text = name,
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
            Text(
                text = price,
                fontWeight = FontWeight(500),
                fontSize = 13.sp,
                lineHeight = 15.sp, color = BlueRibbon
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        IncreaseDecreaseBox(
            text = "-",
            onClick = onDecrease
        )
        QuantityBox(
            text = quantity.toString(),
        )
        IncreaseDecreaseBox(
            text = "+",
            onClick = onIncrease
        )
    }
}