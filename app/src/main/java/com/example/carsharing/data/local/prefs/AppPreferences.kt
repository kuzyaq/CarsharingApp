package com.example.carsharing.data.local.prefs

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.carsharing.domain.model.UserRole
import com.example.carsharing.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Класс для работы с DataStore.
 * Отвечает за:
 * - хранение режима темы
 * - хранение простого состояния авторизации (токен + роль пользователя).
 */
class AppPreferences(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "app_prefs")

    companion object {
        private val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_ROLE_KEY = stringPreferencesKey("user_role")
    }

    /** Текущий режим темы как поток. */
    val themeMode: Flow<ThemeMode> = context.dataStore.data
        .map { prefs ->
            when (prefs[THEME_MODE_KEY]) {
                ThemeMode.LIGHT.name -> ThemeMode.LIGHT
                ThemeMode.DARK.name -> ThemeMode.DARK
                ThemeMode.SYSTEM.name -> ThemeMode.SYSTEM
                else -> ThemeMode.SYSTEM
            }
        }

    /** Токен авторизации (может быть null). */
    val authToken: Flow<String?> = context.dataStore.data
        .map { prefs -> prefs[AUTH_TOKEN_KEY] }

    /** Роль текущего пользователя (может быть null, если не авторизован). */
    val userRole: Flow<UserRole?> = context.dataStore.data
        .map { prefs ->
            when (prefs[USER_ROLE_KEY]) {
                UserRole.CLIENT.name -> UserRole.CLIENT
                UserRole.ADMIN.name -> UserRole.ADMIN
                else -> null
            }
        }

    /** Сохранить режим темы. */
    suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit { prefs ->
            prefs[THEME_MODE_KEY] = mode.name
        }
    }

    /** Сохранить авторизационные данные. */
    suspend fun saveAuth(token: String, role: UserRole) {
        context.dataStore.edit { prefs ->
            prefs[AUTH_TOKEN_KEY] = token
            prefs[USER_ROLE_KEY] = role.name
        }
    }

    /** Очистить авторизацию. */
    suspend fun clearAuth() {
        context.dataStore.edit { prefs ->
            prefs.remove(AUTH_TOKEN_KEY)
            prefs.remove(USER_ROLE_KEY)
        }
    }
}

