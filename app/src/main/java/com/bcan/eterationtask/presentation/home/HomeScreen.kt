package com.bcan.eterationtask.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bcan.eterationtask.presentation.home.components.HomeTopAppBar

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { HomeTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Text(
            text = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }
}
