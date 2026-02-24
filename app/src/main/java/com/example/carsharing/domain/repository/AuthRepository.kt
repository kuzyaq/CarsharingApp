package com.example.carsharing.domain.repository

import com.example.carsharing.domain.model.User
import com.example.carsharing.domain.model.UserRole

/**
 * Репозиторий авторизации.
 * В дальнейшем здесь будет интеграция с Ktor API.
 */
interface AuthRepository {

    /**
     * Вход пользователя с указанной ролью.
     */
    suspend fun login(
        email: String,
        password: String,
        role: UserRole
    ): Result<User>

    /**
     * Регистрация нового клиента.
     */
    suspend fun registerClient(
        email: String,
        password: String,
        fullName: String
    ): Result<User>

    /**
     * Создание администратора (обычно только из-под админа).
     */
    suspend fun registerAdmin(
        email: String,
        password: String,
        fullName: String
    ): Result<User>
}

