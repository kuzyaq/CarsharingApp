package com.example.carsharing.domain.usecase.car

import com.example.carsharing.domain.repository.CarRepository

class RefreshCarsUseCase(
    private val carRepository: CarRepository
) {
    suspend operator fun invoke() = carRepository.refreshCars()
}
