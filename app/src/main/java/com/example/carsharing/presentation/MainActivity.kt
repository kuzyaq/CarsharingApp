package com.example.carsharing.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.carsharing.presentation.screens.CarPageScreen
import com.example.carsharing.presentation.screens.MainScreen
import com.example.carsharing.presentation.screens.PickUpScreen
import com.example.carsharing.presentation.screens.initMapKit
import com.example.carsharing.ui.theme.CarsharingTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initMapKit()
        setContent {
            CarsharingTheme {
                //MainScreen()
                PickUpScreen()
            }
        }
    }
}






