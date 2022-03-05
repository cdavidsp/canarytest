package com.csosa.healiostest.domain.repository

import com.csosa.healiostest.domain.model.ForecastDay
import kotlinx.coroutines.flow.Flow

interface IForecastDaysRepository {

    suspend fun getForecastDays(apiKey: String, query: String, isConnected: Boolean): Flow<List<ForecastDay>>
}