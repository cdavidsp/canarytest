package com.csosa.healiostest.data.remote.models

data class WAForecastDayDay(
    val maxtemp_c: Float?,
    val maxtemp_f: Float?,
    val mintemp_c: Float?,
    val mintemp_f: Float?,
    val avgtemp_c: Float?,
    val avgtemp_f: Float?,
    val maxwind_mph: Float?,
    val maxwind_kph: Float?,
    val totalprecip_mm: Float?,
    val totalprecip_in: Float?,
    val avgvis_km: Float?,
    val avgvis_miles: Float?,
    val avghumidity: Float?,
    val uv: Float?,
    val condition: WAForecastDayDayCondition?

)
