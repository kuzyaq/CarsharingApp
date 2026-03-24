package com.example.carsharing.data.repository

import com.example.carsharing.data.local.db.BookingDao
import com.example.carsharing.data.local.db.toDomain
import com.example.carsharing.data.local.db.toEntity
import com.example.carsharing.data.remote.bookings.BookingsApiService
import com.example.carsharing.data.remote.bookings.CreateBookingRequestDto
import com.example.carsharing.data.remote.bookings.toDto
import com.example.carsharing.data.remote.bookings.toEntity as remoteToEntity
import com.example.carsharing.domain.model.AdminStats
import com.example.carsharing.domain.model.Booking
import com.example.carsharing.domain.model.BookingStatus
import com.example.carsharing.domain.repository.BookingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalBookingRepository(
    private val bookingDao: BookingDao,
    private val bookingsApiService: BookingsApiService? = null
) : BookingRepository {

    override fun observeBookings(userId: String): Flow<List<Booking>> {
        return bookingDao.observeBookings(userId).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun createBooking(booking: Booking): Result<Unit> {
        return runCatching {
            bookingsApiService?.createBooking(
                CreateBookingRequestDto(
                    carId = booking.carId,
                    startDate = booking.startDate,
                    endDate = booking.endDate,
                    totalPrice = booking.totalPrice
                )
            )?.let { remote ->
                bookingDao.upsertAll(listOf(remote.remoteToEntity()))
                return@runCatching
            }
            bookingDao.upsertAll(listOf(booking.toEntity()))
        }
    }

    override suspend fun refreshMyBookings(): Result<Unit> {
        return runCatching {
            val remote = bookingsApiService?.getMyBookings() ?: return@runCatching
            bookingDao.upsertAll(remote.map { it.remoteToEntity() })
        }
    }

    override suspend fun cancelBooking(bookingId: String): Result<Unit> {
        return runCatching {
            bookingsApiService?.cancelBooking(bookingId)
            refreshMyBookings()
        }
    }

    override fun observeAllBookings(): Flow<List<Booking>> =
        bookingDao.observeAllBookings().map { list -> list.map { it.toDomain() } }

    override suspend fun refreshAllBookings(): Result<Unit> {
        return runCatching {
            val remote = bookingsApiService?.getAdminBookings() ?: return@runCatching
            bookingDao.upsertAll(remote.map { it.remoteToEntity() })
        }
    }

    override suspend fun updateBookingStatus(bookingId: String, status: BookingStatus): Result<Unit> {
        return runCatching {
            bookingsApiService?.updateBookingStatus(bookingId, status.toDto())
            refreshAllBookings()
        }
    }

    override suspend fun getAdminStats(): Result<AdminStats> {
        return runCatching {
            val stats = bookingsApiService?.getAdminStats()
            if (stats != null) {
                AdminStats(
                    totalCars = stats.totalCars,
                    activeBookings = stats.activeBookings,
                    pendingBookings = stats.pendingBookings
                )
            } else {
                AdminStats(totalCars = 0, activeBookings = 0, pendingBookings = 0)
            }
        }
    }
}
