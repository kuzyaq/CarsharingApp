package com.example.carsharing.data.remote.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

/**
 * Сервис для работы с Auth REST API на базе Ktor-клиента.
 */
class AuthApiService(
    private val client: HttpClient,
    private val baseUrl: String
) {

    /**
     * Логин клиента.
     */
    suspend fun loginClient(email: String, password: String): AuthResponseDto {
        val response: HttpResponse = client.post("$baseUrl/auth/client/login") {
            setBody(AuthRequestDto(email = email, password = password))
        }
        return response.body()
    }

    /**
     * Логин администратора.
     * Сейчас на сервере нет отдельного эндпоинта, поэтому используется тот же, что и для клиента,
     * но оставляем метод для будущего расширения.
     */
    suspend fun loginAdmin(email: String, password: String): AuthResponseDto {
        val response: HttpResponse = client.post("$baseUrl/auth/client/login") {
            setBody(AuthRequestDto(email = email, password = password))
        }
        return response.body()
    }

    // Методы регистрации можно добавить позже, когда появятся соответствующие эндпоинты на сервере.
}

