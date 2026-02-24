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

/**
 * Админский дашборд: точка входа для администратора.
 * Здесь будут виджеты статистики и быстрые действия.
 */
@Composable
fun AdminDashboardScreen(
    onFleetClick: () -> Unit = {},
    onBookingsClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Админский дашборд")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onFleetClick) {
            Text(text = "Управление автопарком")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onBookingsClick) {
            Text(text = "Управление бронированиями")
        }
    }
}

@Preview
@Composable
private fun PreviewAdminDashboard() {
    AdminDashboardScreen()
}

