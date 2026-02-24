package com.example.carsharing.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Главная база данных Room.
 * Хранит автомобили и бронирования для оффлайн-режима.
 */
@Database(
    entities = [
        CarEntity::class,
        BookingEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    abstract fun bookingDao(): BookingDao
}

