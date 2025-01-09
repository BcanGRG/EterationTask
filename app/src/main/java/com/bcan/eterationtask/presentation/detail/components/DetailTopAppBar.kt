package com.bcan.eterationtask.presentation.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcan.eterationtask.R
import com.bcan.eterationtask.ui.theme.BlueRibbon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    title: String?,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title.orEmpty(),
                modifier = Modifier
                    .padding(end = 72.dp, start = 36.dp)
                    .fillMaxWidth(),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight(800), lineHeight = 22.sp,
                textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis, maxLines = 1
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 18.dp)
                    .clickable { onBackClick() },
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "Back", tint = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = BlueRibbon),
    )
}