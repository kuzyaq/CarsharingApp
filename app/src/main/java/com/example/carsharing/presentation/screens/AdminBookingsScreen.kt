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
 * Экран управления бронированиями для администратора.
 * Здесь админ будет менять статусы аренды, просматривать список и фильтровать его.
 */
@Composable
fun AdminBookingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Управление бронированиями (Admin Bookings)")
    }
}

@Preview
@Composable
private fun PreviewAdminBookings() {
    AdminBookingsScreen()
}

