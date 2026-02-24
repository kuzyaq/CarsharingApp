package com.example.carsharing.domain.model

/**
 * Доменная модель автомобиля.
 * Используется для отображения, локального хранения и сетевых операций.
 */
data class Car(
    val id: String,
    val brand: String,
    val model: String,
    val year: Int,
    val pricePerDay: Double,
    val imageUrl: String? = null,
    val isAvailable: Boolean = true,
    val location: String,
    val mileage: Int? = null,
    val description: String? = null
)

