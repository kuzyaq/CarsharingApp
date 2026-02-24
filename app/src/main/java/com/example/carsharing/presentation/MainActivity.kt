package com.example.carsharing.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.carsharing.presentation.screens.initMapKit
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        actionBar?.hide()

        var isSplashScreenVisible = true
        splashScreen.setKeepOnScreenCondition { isSplashScreenVisible }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initMapKit()

        // Симуляция загрузки данных
        lifecycleScope.launchWhenCreated {
            delay(3000)
            isSplashScreenVisible = false //
        }


        setContent {
            CarsharingApp()
        }
    }
}






