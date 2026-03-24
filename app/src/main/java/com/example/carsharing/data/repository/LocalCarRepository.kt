package com.example.carsharing.data.repository

import com.example.carsharing.data.local.db.CarDao
import com.example.carsharing.data.local.db.CarEntity
import com.example.carsharing.data.local.db.toEntity
import com.example.carsharing.data.local.db.toDomain
import com.example.carsharing.data.remote.cars.CarsApiService
import com.example.carsharing.data.remote.cars.UpsertCarRequestDto
import com.example.carsharing.data.remote.cars.toEntity as remoteToEntity
import com.example.carsharing.domain.model.Car
import com.example.carsharing.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalCarRepository(
    private val carDao: CarDao,
    private val carsApiService: CarsApiService? = null
) : CarRepository {

    override fun observeCars(query: String): Flow<List<Car>> {
        val dbQuery = "%${query.trim()}%"
        return carDao.observeCars(dbQuery).map { list -> list.map { it.toDomain() } }
    }

    override fun observeCarById(carId: String): Flow<Car?> {
        return carDao.observeCarById(carId).map { it?.toDomain() }
    }

    override suspend fun refreshCars() {
        if (carsApiService != null) {
            runCatching { carsApiService?.getCars() ?: emptyList() }
                .onSuccess { remoteCars ->
                    carDao.upsertAll(remoteCars.map { it.remoteToEntity() })
                    return
                }
        }
        val demoCars = listOf(
            CarEntity(
                id = "lixiang_l7",
                brand = "Lixiang",
                model = "L7",
                year = 2024,
                pricePerDay = 12000.0,
                imageUrl = "lixiang_l7",
                isAvailable = true,
                location = "Москва",
                mileage = 15000,
                description = "Комфортный семейный кроссовер."
            ),
            CarEntity(
                id = "mercedes_s450_coupe",
                brand = "Mercedes",
                model = "S450 Coupe",
                year = 2022,
                pricePerDay = 7000.0,
                imageUrl = "mercedes_s450_coupe",
                isAvailable = true,
                location = "Москва",
                mileage = 38000,
                description = "Премиальное купе для города и трассы."
            ),
            CarEntity(
                id = "dodge_challenger",
                brand = "Dodge",
                model = "Challenger",
                year = 2021,
                pricePerDay = 6000.0,
                imageUrl = "dodge_challenger",
                isAvailable = true,
                location = "Казань",
                mileage = 52000,
                description = "Мощный спорткар с узнаваемым стилем."
            ),
            CarEntity(
                id = "range_rover_evoque",
                brand = "Range Rover",
                model = "Evoque",
                year = 2023,
                pricePerDay = 9000.0,
                imageUrl = "range_rover_evoque",
                isAvailable = true,
                location = "Санкт-Петербург",
                mileage = 21000,
                description = "Компактный SUV с премиальным салоном."
            )
        )
        carDao.upsertAll(demoCars)
    }

    override suspend fun addCar(car: Car): Result<Car> {
        return runCatching {
            carsApiService?.createCar(
                UpsertCarRequestDto(
                    brand = car.brand,
                    model = car.model,
                    year = car.year,
                    pricePerDay = car.pricePerDay,
                    imageUrl = car.imageUrl,
                    isAvailable = car.isAvailable,
                    location = car.location,
                    mileage = car.mileage,
                    description = car.description
                )
            )
            carDao.upsertAll(listOf(car.toEntity()))
            car
        }
    }

    override suspend fun updateCar(car: Car): Result<Car> {
        return runCatching {
            carsApiService?.updateCar(
                carId = car.id,
                request = UpsertCarRequestDto(
                    brand = car.brand,
                    model = car.model,
                    year = car.year,
                    pricePerDay = car.pricePerDay,
                    imageUrl = car.imageUrl,
                    isAvailable = car.isAvailable,
                    location = car.location,
                    mileage = car.mileage,
                    description = car.description
                )
            )
            carDao.upsertAll(listOf(car.toEntity()))
            car
        }
    }

    override suspend fun deleteCar(carId: String): Result<Unit> {
        return runCatching {
            carsApiService?.deleteCar(carId)
            carDao.deleteById(carId)
        }
    }
}
