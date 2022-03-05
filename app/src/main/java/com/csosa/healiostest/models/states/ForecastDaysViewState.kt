package com.csosa.healiostest.models.states

import com.csosa.healiostest.models.ForecastDayPresentation

internal data class ForecastDaysViewState(
    val isLoading: Boolean,
    val error: Error?,
    val forecastDays: List<ForecastDayPresentation>?
)
