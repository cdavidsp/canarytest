package com.csosa.healiostest.di

import com.csosa.healiostest.viewmodel.ForecastDayDetailViewModel
import com.csosa.healiostest.viewmodel.ForecastDaysViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        ForecastDaysViewModel(
            getForecastDaysUseCase = get(named("forecast_days"))
        )
    }
    viewModel {
        ForecastDayDetailViewModel()
    }


}
