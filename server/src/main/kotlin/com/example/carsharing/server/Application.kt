package com.example.carsharing.server

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.callloging.CallLogging
import kotlinx.serialization.Serializable
import org.slf4j.event.Level

/**
 * Точка входа Ktor-сервера.
 * Здесь настраиваются плагины и базовые маршруты.
 */
fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(CallLogging) {
        level = Level.INFO
    }
    install(ContentNegotiation) {
        json()
    }

    routing {
        // Пример простого health-check эндпоинта
        get("/health") {
            call.respond(mapOf("status" to "OK"))
        }

        // Базовый каркас контроллера авторизации
        post("/auth/client/login") {
            val request = call.receive<AuthRequest>()
            // TODO: здесь будет вызов AuthService.loginClient
            val fakeUser = UserDto(
                id = "1",
                email = request.email,
                fullName = "Demo Client",
                role = UserRoleDto.CLIENT
            )
            call.respond(AuthResponse(token = "demo-token", user = fakeUser))
        }
    }
}

// Ниже – простые DTO для сервера (в дальнейшем их можно вынести в отдельные файлы)

@Serializable
data class AuthRequest(
    val email: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val token: String,
    val user: UserDto
)

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    val fullName: String,
    val role: UserRoleDto
)

@Serializable
enum class UserRoleDto {
    CLIENT,
    ADMIN
}

