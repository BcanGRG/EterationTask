package com.bcan.eterationtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.bcan.eterationtask.presentation.utils.navigation.EterationTaskNavigation
import com.bcan.eterationtask.ui.theme.EterationTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EterationTaskTheme {
                window.statusBarColor = Color.White.toArgb()
                EterationTaskNavigation()
            }
        }
    }
}