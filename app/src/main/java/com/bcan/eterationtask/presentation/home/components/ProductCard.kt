package com.bcan.eterationtask.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bcan.eterationtask.R
import com.bcan.eterationtask.ui.theme.Alto
import com.bcan.eterationtask.ui.theme.BlueRibbon
import com.bcan.eterationtask.ui.theme.SelectiveYellow
import com.bcan.eterationtask.ui.theme.Silver

@Composable
fun ProductCard(
    imageUrl: String?,
    price: String?,
    isFavorite: Boolean = false,
    productName: String?,
    onClick: () -> Unit = {},
    onAddToCart: () -> Unit = {},
    onClickFavorite: () -> Unit = {},
) {
    Card(
        modifier = Modifier.clickable { onClick() },
        shape = RectangleShape,
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "ProductCard image url",
                    modifier = Modifier
                        .size(150.dp)
                        .background(Silver),
                    contentScale = ContentScale.FillBounds
                )
                Icon(
                    painter = painterResource(R.drawable.ic_favorite),
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .clickable { onClickFavorite() },
                    tint = if (isFavorite) SelectiveYellow else Alto
                )
            }
            Text(
                text = price.orEmpty() + " ₺",
                fontWeight = FontWeight(500),
                fontSize = 14.sp,
                color = BlueRibbon,
            )
            Text(
                text = productName.orEmpty(),
                fontWeight = FontWeight(500),
                fontSize = 14.sp, lineHeight = 17.sp
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onAddToCart,
                colors = ButtonDefaults.buttonColors(containerColor = BlueRibbon),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = "Add to Cart",
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp, lineHeight = 20.sp
                )
            }
        }
    }
}