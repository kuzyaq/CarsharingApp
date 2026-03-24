package com.example.carsharing.data.remote.cars

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class CarsApiService(
    private val client: HttpClient,
    private val baseUrl: String
) {
    suspend fun getCars(query: String = ""): List<CarDto> =
        client.get("$baseUrl/cars") {
            if (query.isNotBlank()) parameter("query", query)
        }.body()

    suspend fun getCarById(carId: String): CarDto =
        client.get("$baseUrl/cars/$carId").body()

    suspend fun createCar(request: UpsertCarRequestDto): CarDto =
        client.post("$baseUrl/admin/cars") { setBody(request) }.body()

    suspend fun updateCar(carId: String, request: UpsertCarRequestDto): CarDto =
        client.put("$baseUrl/admin/cars/$carId") { setBody(request) }.body()

    suspend fun deleteCar(carId: String) {
        client.delete("$baseUrl/admin/cars/$carId")
    }
}
