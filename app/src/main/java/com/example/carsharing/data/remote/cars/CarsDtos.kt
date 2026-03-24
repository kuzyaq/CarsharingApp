package com.example.carsharing.data.remote.cars

import com.example.carsharing.data.local.db.CarEntity
import kotlinx.serialization.Serializable

@Serializable
data class CarDto(
    val id: String,
    val brand: String,
    val model: String,
    val year: Int,
    val pricePerDay: Double,
    val imageUrl: String? = null,
    val isAvailable: Boolean,
    val location: String,
    val mileage: Int? = null,
    val description: String? = null
)

@Serializable
data class UpsertCarRequestDto(
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

fun CarDto.toEntity(): CarEntity =
    CarEntity(
        id = id,
        brand = brand,
        model = model,
        year = year,
        pricePerDay = pricePerDay,
        imageUrl = imageUrl,
        isAvailable = isAvailable,
        location = location,
        mileage = mileage,
        description = description
    )
