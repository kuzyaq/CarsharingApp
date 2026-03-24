package com.example.carsharing.domain.usecase.car

import com.example.carsharing.domain.repository.CarRepository

class ObserveCarsUseCase(
    private val carRepository: CarRepository
) {
    operator fun invoke(query: String) = carRepository.observeCars(query)
}
