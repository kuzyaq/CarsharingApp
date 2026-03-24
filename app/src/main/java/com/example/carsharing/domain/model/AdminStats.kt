package com.example.carsharing.domain.model

data class AdminStats(
    val totalCars: Int,
    val activeBookings: Int,
    val pendingBookings: Int
)
