package com.example.carsharing.domain.usecase.auth

import com.example.carsharing.domain.repository.AuthRepository

class RegisterClientUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(fullName: String, email: String, password: String) =
        authRepository.registerClient(email = email, password = password, fullName = fullName)
}
