package com.csosa.healiostest.domain.model

data class ForecastDay(
    val id: Long,
    val lat: Double?,
    val lon: Double?,

    val locationName: String?,
    val locationCountry: String?,

    val date: String?,
    val maxtemp_c: Float?,
    val mintemp_c: Float?,
    val avgtemp_c: Float?,


    val maxwind_kph: Float?,
    val totalprecip_mm: Float?,
    val avghumidity: Float?,

    val conditionText: String?,
    val conditionIcon: String?,

    val sunrise: String?,
    val sunset: String?,
    val moonrise: String?,
    val moonset: String?,
    val moon_phase: String?,
    val moon_illumination: String?
)
