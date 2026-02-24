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
 * Экран управления автопарком.
 * В дальнейшем здесь появятся CRUD-операции по автомобилям.
 */
@Composable
fun FleetManagementScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Управление автопарком (Fleet Management)")
    }
}

@Preview
@Composable
private fun PreviewFleet() {
    FleetManagementScreen()
}

