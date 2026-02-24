package com.example.carsharing.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carsharing.data.local.prefs.AppPreferences
import com.example.carsharing.data.remote.auth.AuthApiService
import com.example.carsharing.data.remote.network.HttpClientProvider
import com.example.carsharing.data.repository.AuthRepositoryImpl
import com.example.carsharing.domain.model.UserRole
import com.example.carsharing.presentation.screens.AdminBookingsScreen
import com.example.carsharing.presentation.screens.AdminDashboardScreen
import com.example.carsharing.presentation.screens.BookingConfirmationScreen
import com.example.carsharing.presentation.screens.CarPageScreen
import com.example.carsharing.presentation.screens.FleetManagementScreen
import com.example.carsharing.presentation.screens.LoginScreen
import com.example.carsharing.presentation.screens.MainScreen
import com.example.carsharing.presentation.screens.MyBookingsScreen
import com.example.carsharing.presentation.screens.PickUpScreen
import com.example.carsharing.presentation.screens.ProfileScreen
import com.example.carsharing.presentation.screens.RegisterScreen
import com.example.carsharing.presentation.screens.SettingsScreen
import kotlinx.coroutines.launch

/**
 * Главный граф навигации приложения.
 * Сейчас реализованы базовые переходы:
 * - Login -> Register
 * - Login/Register -> Home (MainScreen)
 * - Home -> CarDetails (CarPageScreen как заглушка)
 *
 * Далее сюда будут добавлены остальные 10 экранов
 * и разграничение по ролям (клиент/админ).
 */
@Composable
fun CarSharingNavGraph(
    navController: NavHostController,
    startDestination: String,
    currentUserRole: UserRole?
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Экран логина
        composable(Screen.Login.route) {
            val context = LocalContext.current
            val appPreferences = remember { AppPreferences(context) }
            val scope = rememberCoroutineScope()
            val authRepository = remember {
                AuthRepositoryImpl(
                    authApiService = AuthApiService(
                        client = HttpClientProvider.client,
                        baseUrl = HttpClientProvider.BASE_URL
                    ),
                    appPreferences = appPreferences
                )
            }

            LoginScreen(
                onLoginClick = { email, password, role ->
                    // Вызываем AuthRepository и в случае успеха переходим на нужный экран
                    scope.launch {
                        val result = authRepository.login(email, password, role)
                        result.onSuccess {
                            val target = when (role) {
                                UserRole.CLIENT -> Screen.Home.route
                                UserRole.ADMIN -> Screen.AdminDashboard.route
                            }
                            navController.navigate(target) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                        // Ошибку сейчас игнорируем, в будущем можно добавить отображение
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        // Экран регистрации
        composable(Screen.Register.route) {
            val context = LocalContext.current
            val appPreferences = remember { AppPreferences(context) }
            val scope = rememberCoroutineScope()
            val authRepository = remember {
                AuthRepositoryImpl(
                    authApiService = AuthApiService(
                        client = HttpClientProvider.client,
                        baseUrl = HttpClientProvider.BASE_URL
                    ),
                    appPreferences = appPreferences
                )
            }

            RegisterScreen(
                onRegisterClick = { fullName, email, password ->
                    scope.launch {
                        val result = authRepository.registerClient(
                            email = email,
                            password = password,
                            fullName = fullName
                        )
                        result.onSuccess {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                        // Ошибку сейчас игнорируем
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        // Главный экран клиента (Home)
        composable(Screen.Home.route) {
            MainScreen(
                onCarClick = { carId ->
                    navController.navigate(Screen.CarDetails.create(carId))
                },
                onProfileClick = {
                    navController.navigate(Screen.UserProfile.route)
                }
            )
        }

        // Детали автомобиля (пока использует существующий CarPageScreen как заглушку)
        composable(Screen.CarDetails.route) { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId") ?: ""
            CarPageScreen(
                carId = carId,
                onBackClick = { navController.popBackStack() },
                onPickUpClick = { selectedCarId ->
                    navController.navigate(Screen.BookingConfirmation.create(selectedCarId))
                }
            )
        }

        // Экран выбора места выдачи (PickUpScreen) — временно как отдельный маршрут
        composable("pickup") {
            PickUpScreen()
        }

        // Экран подтверждения брони
        composable(Screen.BookingConfirmation.route) { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId") ?: ""
            BookingConfirmationScreen(
                carId = carId,
                onConfirmClick = {
                    // TODO: создать бронь и перейти на экран "Мои аренды"
                    navController.navigate(Screen.MyBookings.route) {
                        popUpTo(Screen.Home.route)
                    }
                },
                onCancelClick = { navController.popBackStack() }
            )
        }

        // Экран "Мои аренды"
        composable(Screen.MyBookings.route) {
            MyBookingsScreen()
        }

        // Админский дашборд
        composable(Screen.AdminDashboard.route) {
            AdminDashboardScreen(
                onFleetClick = { navController.navigate(Screen.FleetManagement.route) },
                onBookingsClick = { navController.navigate(Screen.AdminBookings.route) }
            )
        }

        // Управление автопарком
        composable(Screen.FleetManagement.route) {
            FleetManagementScreen()
        }

        // Управление бронированиями администратора
        composable(Screen.AdminBookings.route) {
            AdminBookingsScreen()
        }

        // Профиль пользователя (пока заглушка)
        composable(Screen.UserProfile.route) {
            ProfileScreen(
                onMyBookingsClick = { navController.navigate(Screen.MyBookings.route) },
                onSettingsClick = { navController.navigate(Screen.Settings.route) }
            )
        }

        // Экран настроек
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}

