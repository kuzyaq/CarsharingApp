package com.example.carsharing.data.repository

import com.example.carsharing.data.local.prefs.AppPreferences
import com.example.carsharing.data.remote.auth.AuthApiService
import com.example.carsharing.data.remote.auth.toDomain
import com.example.carsharing.domain.model.User
import com.example.carsharing.domain.model.UserRole
import com.example.carsharing.domain.repository.AuthRepository

/**
 * Реализация AuthRepository.
 * Выполняет сетевые запросы через Ktor и сохраняет токен/роль в DataStore.
 */
class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val appPreferences: AppPreferences
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String,
        role: UserRole
    ): Result<User> {
        return runCatching {
            val response = when (role) {
                UserRole.CLIENT -> authApiService.loginClient(email, password)
                UserRole.ADMIN -> authApiService.loginAdmin(email, password)
            }

            // Сохраняем токен и роль
            appPreferences.saveAuth(
                token = response.token,
                role = role
            )

            response.user.toDomain()
        }
    }

    override suspend fun registerClient(
        email: String,
        password: String,
        fullName: String
    ): Result<User> {
        // Пока серверной регистрации нет – эмулируем успех
        val fakeUser = User(
            id = "local-client",
            email = email,
            fullName = fullName,
            role = UserRole.CLIENT
        )
        return runCatching {
            appPreferences.saveAuth(token = "local-demo-token", role = UserRole.CLIENT)
            fakeUser
        }
    }

    override suspend fun registerAdmin(
        email: String,
        password: String,
        fullName: String
    ): Result<User> {
        val fakeUser = User(
            id = "local-admin",
            email = email,
            fullName = fullName,
            role = UserRole.ADMIN
        )
        return runCatching {
            appPreferences.saveAuth(token = "local-admin-token", role = UserRole.ADMIN)
            fakeUser
        }
    }
}

