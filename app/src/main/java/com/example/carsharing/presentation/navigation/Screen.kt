package com.example.carsharing.presentation.navigation

/**
 * Описание всех экранов приложения для Navigation-Compose.
 */
sealed class Screen(val route: String) {

    // Авторизация
    data object Login : Screen("login")
    data object Register : Screen("register")

    // Клиент
    data object Home : Screen("home")
    data object CarDetails : Screen("car_details/{carId}") {
        fun create(carId: String) = "car_details/$carId"
    }

    data object BookingConfirmation : Screen("booking_confirmation/{carId}") {
        fun create(carId: String) = "booking_confirmation/$carId"
    }

    data object MyBookings : Screen("my_bookings")
    data object UserProfile : Screen("user_profile")
    data object Settings : Screen("settings")

    // Админ
    data object AdminDashboard : Screen("admin_dashboard")
    data object FleetManagement : Screen("fleet_management")
    data object AdminBookings : Screen("admin_bookings")
}

