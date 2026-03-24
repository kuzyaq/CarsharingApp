package com.example.carsharing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.carsharing.domain.model.Booking
import com.example.carsharing.domain.model.BookingStatus
import com.example.carsharing.domain.repository.BookingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookingsViewModel(
    private val bookingRepository: BookingRepository,
    private val userId: String
) : ViewModel() {
    val myBookings: StateFlow<List<Booking>> =
        bookingRepository.observeBookings(userId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun refreshMy() {
        viewModelScope.launch { bookingRepository.refreshMyBookings() }
    }

    fun cancel(bookingId: String) {
        viewModelScope.launch { bookingRepository.cancelBooking(bookingId) }
    }

    fun markCompleted(bookingId: String) {
        viewModelScope.launch { bookingRepository.updateBookingStatus(bookingId, BookingStatus.COMPLETED) }
    }

    companion object {
        fun factory(
            bookingRepository: BookingRepository,
            userId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BookingsViewModel(bookingRepository, userId) as T
            }
        }
    }
}
