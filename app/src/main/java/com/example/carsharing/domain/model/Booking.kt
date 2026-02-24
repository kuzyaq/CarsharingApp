package com.example.carsharing.domain.model

/**
 * Доменная модель бронирования автомобиля.
 */
data class Booking(
    val id: String,
    val userId: String,
    val carId: String,
    val startDate: Long,          // timestamp начала аренды
    val endDate: Long,            // timestamp окончания аренды
    val status: BookingStatus,
    val totalPrice: Double
)

