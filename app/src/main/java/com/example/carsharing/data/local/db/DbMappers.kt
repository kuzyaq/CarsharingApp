package com.example.carsharing.data.local.db

import com.example.carsharing.domain.model.Booking
import com.example.carsharing.domain.model.BookingStatus
import com.example.carsharing.domain.model.Car

fun CarEntity.toDomain(): Car =
    Car(
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

fun Car.toEntity(): CarEntity =
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

fun BookingEntity.toDomain(): Booking =
    Booking(
        id = id,
        userId = userId,
        carId = carId,
        startDate = startDate,
        endDate = endDate,
        status = BookingStatus.valueOf(status),
        totalPrice = totalPrice
    )

fun Booking.toEntity(): BookingEntity =
    BookingEntity(
        id = id,
        userId = userId,
        carId = carId,
        startDate = startDate,
        endDate = endDate,
        status = status.name,
        totalPrice = totalPrice
    )
