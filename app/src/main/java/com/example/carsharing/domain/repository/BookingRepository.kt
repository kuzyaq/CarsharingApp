package com.example.carsharing.domain.repository

import com.example.carsharing.domain.model.Booking
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для работы с бронированиями.
 */
interface BookingRepository {

    /**
     * Наблюдение за бронированиями конкретного пользователя.
     */
    fun observeBookings(userId: String): Flow<List<Booking>>

    /**
     * Создание нового бронирования.
     */
    suspend fun createBooking(booking: Booking): Result<Unit>
}

