package com.bcan.eterationtask.presentation.ui

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcan.eterationtask.ui.theme.BlueRibbon

@Composable
fun BoxScope.PriceAndButtonComponent(
    price: String?,
    buttonText: String = "Add to Cart",
    onButtonClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.Companion.align(Alignment.BottomCenter)
    ) {
        val priceText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight(400),
                    fontSize = 18.sp,
                    color = BlueRibbon
                )
            ) {
                append("Price: ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp,
                    color = Color.Black,
                )
            ) {
                append("$price ₺")
            }
        }
        Text(text = priceText, modifier = Modifier.weight(48f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(52f),
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors(containerColor = BlueRibbon),
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = buttonText,
                fontWeight = FontWeight(700),
                fontSize = 18.sp, lineHeight = 22.sp
            )
        }
    }
}