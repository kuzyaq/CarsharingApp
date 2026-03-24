package com.example.carsharing.domain.usecase.booking

import com.example.carsharing.domain.model.Booking
import com.example.carsharing.domain.repository.BookingRepository

class CreateBookingUseCase(
    private val bookingRepository: BookingRepository
) {
    suspend operator fun invoke(booking: Booking) = bookingRepository.createBooking(booking)
}
