package com.example.carsharing.domain.usecase.auth

import com.example.carsharing.domain.model.UserRole
import com.example.carsharing.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String, role: UserRole) =
        authRepository.login(email, password, role)
}
