package com.example.carsharing.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Экран "Мои аренды" (активные и история).
 * Пока реализован как простая заглушка.
 */
@Composable
fun MyBookingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Мои аренды (активные и история)")
    }
}

@Preview
@Composable
private fun PreviewMyBookings() {
    MyBookingsScreen()
}

