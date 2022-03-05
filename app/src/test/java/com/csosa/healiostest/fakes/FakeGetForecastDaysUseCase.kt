package com.csosa.healiostest.fakes

import com.csosa.healiostest.domain.model.ForecastDay
import com.csosa.healiostest.domain.usecases.GetForecastDaysBaseUseCase
import com.csosa.healiostest.domain.usecases.GetForecastDaysUseCaseInput
import com.csosa.healiostest.utils.Data
import com.csosa.healiostest.utils.UiState
import kotlinx.coroutines.flow.Flow

class FakeGetForecastDaysUseCase(
    uiState: UiState
) : BaseTestUseCase<List<ForecastDay>, GetForecastDaysUseCaseInput>(uiState), GetForecastDaysBaseUseCase {

    override suspend fun invoke(params: GetForecastDaysUseCaseInput): Flow<List<ForecastDay>> = execute(params)

    override fun getValue(params: GetForecastDaysUseCaseInput): List<ForecastDay> = Data.forecastDays
}
