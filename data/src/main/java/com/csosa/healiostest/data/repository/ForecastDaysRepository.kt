package com.csosa.healiostest.data.repository

import com.csosa.healiostest.data.local.dao.ForecastDaysDao
import com.csosa.healiostest.data.local.mappers.toDomain
import com.csosa.healiostest.data.local.mappers.toEntity
import com.csosa.healiostest.data.remote.api.HealiosApiService
import com.csosa.healiostest.data.remote.mappers.toDomain
import com.csosa.healiostest.domain.model.ForecastDay
import com.csosa.healiostest.domain.repository.IForecastDaysRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ForecastDaysRepository(
    private val apiService: HealiosApiService,
    private val forecastDaysDao: ForecastDaysDao
) : IForecastDaysRepository {


    override suspend fun getForecastDays(
        apiKey: String,
        query: String,
        isConnected: Boolean
    ): Flow<List<ForecastDay>> = flow {

        if (isConnected) {

            forecastDaysDao.deleteAll()
            val forecastWeatherResponse = apiService.getForecastWeather(apiKey, query, 7)
            for (forecastDayResponse in forecastWeatherResponse.forecast.forecastday) {

                if (forecastDayResponse.date != null) {

                    val forecastDayEntity = forecastDayResponse.toDomain().toEntity()
                    forecastDayEntity.lat = forecastWeatherResponse.location.lat
                    forecastDayEntity.lon = forecastWeatherResponse.location.lon
                    forecastDayEntity.locationName = forecastWeatherResponse.location.name
                    forecastDayEntity.locationCountry = forecastWeatherResponse.location.country


                    forecastDaysDao.insert(forecastDayEntity)
                }
            }

        }

        val forecastDaysFromDB = forecastDaysDao.getAll()
        emit(forecastDaysFromDB.map { it.toDomain() })
    }


}
