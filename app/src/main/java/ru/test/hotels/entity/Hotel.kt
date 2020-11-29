package ru.test.hotels.entity

import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("stars") val stars: Float,
    @SerializedName("suites_availability") val suitesAvailability: String,
    @SerializedName("distance") val distance: Double,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("lon") val lon: String
)