package com.example.carsharing.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    onMyBookingsClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    // Базовая заглушка профиля с переходами к "Моим арендам" и "Настройкам"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Профиль пользователя")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onMyBookingsClick) {
            Text(text = "Мои аренды")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onSettingsClick) {
            Text(text = "Настройки")
        }
    }
}

@Preview
@Composable
private fun Preview(){
    ProfileScreen()
}

