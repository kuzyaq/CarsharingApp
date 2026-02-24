package com.example.carsharing.presentation

import android.view.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.carsharing.data.local.prefs.AppPreferences
import com.example.carsharing.domain.model.UserRole
import com.example.carsharing.presentation.navigation.CarSharingNavGraph
import com.example.carsharing.presentation.navigation.Screen
import com.example.carsharing.ui.theme.CarsharingTheme
import com.example.carsharing.ui.theme.ThemeMode

/**
 * Корневой composable всего приложения.
 * Здесь подключаем DataStore (тема, авторизация), NavController и NavGraph.
 */
@Composable
fun CarsharingApp() {
    val context = LocalContext.current
    val appPreferences = remember { AppPreferences(context) }

    val themeMode by appPreferences.themeMode.collectAsState(initial = ThemeMode.SYSTEM)
    val currentUserRole by appPreferences.userRole.collectAsState(initial = null)
    val authToken by appPreferences.authToken.collectAsState(initial = null)

    val navController = rememberNavController()

    // Определяем стартовый экран: если пользователь уже авторизован, отправляем сразу на нужный раздел
    val startDestination = remember(authToken, currentUserRole) {
        if (authToken != null && currentUserRole != null) {
            when (currentUserRole) {
                UserRole.CLIENT -> Screen.Home.route
                UserRole.ADMIN -> Screen.AdminDashboard.route
                null -> TODO()
            }
        } else {
            Screen.Login.route
        }
    }

    CarsharingTheme(themeMode = themeMode) {
        Surface(modifier = Modifier.fillMaxSize()) {
            CarSharingNavGraph(
                navController = navController,
                startDestination = startDestination,
                currentUserRole = currentUserRole
            )
        }
    }
}

