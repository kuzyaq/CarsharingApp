package com.example.carsharing.domain.model

/**
 * Пользователь системы аренды.
 * Данная модель предполагается общей для клиента и сервера.
 */
data class User(
    val id: String,
    val email: String,
    val fullName: String,
    val role: UserRole
)

