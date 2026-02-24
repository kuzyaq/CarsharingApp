package com.example.carsharing.domain.model

/**
 * Статус бронирования автомобиля.
 */
enum class BookingStatus {
    PENDING,
    CONFIRMED,
    IN_PROGRESS,
    COMPLETED,
    CANCELED
}

