package com.csosa.healiostest.models.states

import com.csosa.healiostest.models.ForecastDayPresentation

internal data class ForecastDayDetailsViewState(
    val isComplete: Boolean,
    val error: Error?,
    val info: ForecastDayPresentation?
)