package com.example.carsharing.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity для локального хранения автомобилей.
 * Позволяет реализовать офлайн-режим.
 */
@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey val id: String,
    val brand: String,
    val model: String,
    val year: Int,
    val pricePerDay: Double,
    val imageUrl: String?,
    val isAvailable: Boolean,
    val location: String,
    val mileage: Int?,
    val description: String?
)

