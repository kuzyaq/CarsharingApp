package com.example.carsharing.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * DAO для работы с таблицей бронирований.
 */
@Dao
interface BookingDao {

    @Query("SELECT * FROM bookings WHERE userId = :userId")
    fun observeBookings(userId: String): Flow<List<BookingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(bookings: List<BookingEntity>)

    @Query("DELETE FROM bookings")
    suspend fun clearAll()
}

