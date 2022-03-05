package com.csosa.healiostest.domain.model

data class ForecastDays(
    val forecastDays: List<ForecastDay>,
    val locationName: String?,
    val locationRegion: String?

)
