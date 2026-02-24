package com.example.carsharing.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * DAO для работы с таблицей автомобилей.
 */
@Dao
interface CarDao {

    @Query("SELECT * FROM cars WHERE brand LIKE :query OR model LIKE :query")
    fun observeCars(query: String): Flow<List<CarEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(cars: List<CarEntity>)

    @Query("DELETE FROM cars")
    suspend fun clearAll()
}

