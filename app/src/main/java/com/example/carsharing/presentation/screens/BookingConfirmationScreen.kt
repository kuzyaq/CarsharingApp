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
 * Экран подтверждения бронирования.
 * В дальнейшем здесь появится форма выбора дат, оплаты и подтверждения.
 */
@Composable
fun BookingConfirmationScreen(
    carId: String,
    onConfirmClick: () -> Unit = {},
    onCancelClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Оформление брони для автомобиля: $carId")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onConfirmClick) {
            Text(text = "Подтвердить бронь")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onCancelClick) {
            Text(text = "Отменить")
        }
    }
}

@Preview
@Composable
private fun PreviewBookingConfirmation() {
    BookingConfirmationScreen(carId = "demo-id")
}

