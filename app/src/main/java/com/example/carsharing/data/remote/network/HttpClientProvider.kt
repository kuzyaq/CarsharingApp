package com.example.carsharing.data.remote.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Провайдер Ktor HttpClient для Android.
 * Клиент настраивается один раз и переиспользуется во всём приложении.
 */
object HttpClientProvider {

    val client: HttpClient by lazy {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }

    /**
     * Базовый URL для локального сервера Ktor.
     * Для эмулятора Android localhost – это 10.0.2.2.
     */
    const val BASE_URL: String = "http://10.0.2.2:8080"
}

