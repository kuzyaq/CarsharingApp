package com.example.carsharing.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity для локального хранения бронирований.
 */
@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val carId: String,
    val startDate: Long,
    val endDate: Long,
    val status: String,
    val totalPrice: Double
)

