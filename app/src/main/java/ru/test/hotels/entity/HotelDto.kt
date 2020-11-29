package ru.test.hotels.entity

data class HotelDto(
    val id: Long,
    val name: String,
    val address: String,
    val stars: Float,
    val suitesAvailability: List<String>,
    val distance: Double,
    val imageUrl: String?,
    val lat: String?,
    val lon: String?
)