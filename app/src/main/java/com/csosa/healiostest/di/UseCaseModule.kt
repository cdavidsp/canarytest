package com.csosa.healiostest.di

import com.csosa.healiostest.domain.repository.IForecastDaysRepository
import com.csosa.healiostest.domain.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCasesModule = module {

    single(named("forecast_days")) { provideGetForecastDaysUseCase(get()) }

}

fun provideGetForecastDaysUseCase(forecastDaysRepository: IForecastDaysRepository): GetForecastDaysBaseUseCase {
    return GetForecastDaysUseCase(forecastDaysRepository)
}
