package com.example.carsharing.data.remote.auth

import com.example.carsharing.domain.model.User
import com.example.carsharing.domain.model.UserRole
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DTO для работы с Auth API через Ktor.
 */

@Serializable
data class AuthRequestDto(
    val email: String,
    val password: String
)

@Serializable
data class AuthResponseDto(
    val token: String,
    val user: UserDto
)

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    @SerialName("fullName") val fullName: String,
    val role: UserRoleDto
)

@Serializable
enum class UserRoleDto {
    CLIENT,
    ADMIN
}

/**
 * Маппинг DTO -> доменная модель.
 */
fun UserDto.toDomain(): User =
    User(
        id = id,
        email = email,
        fullName = fullName,
        role = when (role) {
            UserRoleDto.CLIENT -> UserRole.CLIENT
            UserRoleDto.ADMIN -> UserRole.ADMIN
        }
    )

