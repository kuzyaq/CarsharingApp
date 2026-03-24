package com.example.carsharing.data.remote.bookings

import com.example.carsharing.data.local.db.BookingEntity
import com.example.carsharing.domain.model.BookingStatus
import kotlinx.serialization.Serializable

@Serializable
data class BookingDto(
    val id: String,
    val userId: String,
    val carId: String,
    val startDate: Long,
    val endDate: Long,
    val status: BookingStatusDto,
    val totalPrice: Double
)

@Serializable
enum class BookingStatusDto {
    PENDING,
    CONFIRMED,
    IN_PROGRESS,
    COMPLETED,
    CANCELED
}

@Serializable
data class CreateBookingRequestDto(
    val carId: String,
    val startDate: Long,
    val endDate: Long,
    val totalPrice: Double
)

@Serializable
data class UpdateBookingStatusRequestDto(
    val status: BookingStatusDto
)

@Serializable
data class AdminStatsDto(
    val totalCars: Int,
    val activeBookings: Int,
    val pendingBookings: Int
)

fun BookingDto.toEntity(): BookingEntity =
    BookingEntity(
        id = id,
        userId = userId,
        carId = carId,
        startDate = startDate,
        endDate = endDate,
        status = status.name,
        totalPrice = totalPrice
    )

fun BookingStatus.toDto(): BookingStatusDto =
    when (this) {
        BookingStatus.PENDING -> BookingStatusDto.PENDING
        BookingStatus.CONFIRMED -> BookingStatusDto.CONFIRMED
        BookingStatus.IN_PROGRESS -> BookingStatusDto.IN_PROGRESS
        BookingStatus.COMPLETED -> BookingStatusDto.COMPLETED
        BookingStatus.CANCELED -> BookingStatusDto.CANCELED
    }
