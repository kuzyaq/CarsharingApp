package com.example.carsharing.domain.repository

import com.example.carsharing.domain.model.Car
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для работы с автомобилями (поиск, список, детали).
 */
interface CarRepository {

    /**
     * Наблюдение за списком автомобилей по строке поиска.
     * Под капотом может комбинировать локальные данные (Room) и сеть (Ktor).
     */
    fun observeCars(query: String): Flow<List<Car>>

    /**
     * Форсированное обновление списка с сервера.
     */
    suspend fun refreshCars()
}

