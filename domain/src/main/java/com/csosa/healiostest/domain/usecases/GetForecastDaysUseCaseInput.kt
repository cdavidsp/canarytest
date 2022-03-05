package com.csosa.healiostest.domain.usecases

data class GetForecastDaysUseCaseInput(
    val apiKey: String,
    val query: String,
    val isConnected: Boolean
)
