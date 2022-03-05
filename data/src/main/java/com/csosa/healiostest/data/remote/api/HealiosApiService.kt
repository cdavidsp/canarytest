package com.csosa.healiostest.data.remote.api

import com.csosa.healiostest.data.remote.models.WAForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HealiosApiService {

    @GET("v1/forecast.json")
    suspend fun getForecastWeather(
        @Query("key") key: String?,
        @Query("q") q: String?,
        @Query("days") days: Int?
    ): WAForecastResponse

}
