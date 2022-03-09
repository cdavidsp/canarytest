package com.csosa.healiostest.domain.usecases

import com.csosa.healiostest.domain.model.ForecastDay
import com.csosa.healiostest.domain.repository.IForecastDaysRepository
import kotlinx.coroutines.flow.Flow

typealias GetForecastDaysBaseUseCase = BaseUseCase<GetForecastDaysUseCaseInput, Flow<List<ForecastDay>>>

class GetForecastDaysUseCase(
    private val forecastDaysRepository: IForecastDaysRepository
) : GetForecastDaysBaseUseCase {

    override suspend fun invoke(params: GetForecastDaysUseCaseInput) =
        forecastDaysRepository.getForecastDays(params.apiKey, params.query, params.isConnected)

}
