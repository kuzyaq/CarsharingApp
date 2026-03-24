package com.example.carsharing.data.remote.bookings

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class BookingsApiService(
    private val client: HttpClient,
    private val baseUrl: String
) {
    suspend fun createBooking(request: CreateBookingRequestDto): BookingDto =
        client.post("$baseUrl/bookings") { setBody(request) }.body()

    suspend fun getMyBookings(): List<BookingDto> =
        client.get("$baseUrl/bookings/my").body()

    suspend fun cancelBooking(bookingId: String): BookingDto =
        client.patch("$baseUrl/bookings/$bookingId/cancel").body()

    suspend fun getAdminBookings(): List<BookingDto> =
        client.get("$baseUrl/admin/bookings").body()

    suspend fun updateBookingStatus(bookingId: String, status: BookingStatusDto): BookingDto =
        client.patch("$baseUrl/admin/bookings/$bookingId/status") {
            setBody(UpdateBookingStatusRequestDto(status))
        }.body()

    suspend fun getAdminStats(): AdminStatsDto =
        client.get("$baseUrl/admin/stats").body()
}
