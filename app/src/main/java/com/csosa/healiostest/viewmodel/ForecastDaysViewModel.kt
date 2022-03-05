package com.csosa.healiostest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.csosa.healiostest.BuildConfig
import com.csosa.healiostest.commons.ExceptionHandler
import com.csosa.healiostest.domain.usecases.GetForecastDaysBaseUseCase
import com.csosa.healiostest.domain.usecases.GetForecastDaysUseCase
import com.csosa.healiostest.domain.usecases.GetForecastDaysUseCaseInput
import com.csosa.healiostest.idlingresource.EspressoIdlingResource
import com.csosa.healiostest.mappers.toPresentation
import com.csosa.healiostest.models.ForecastDayPresentation
import com.csosa.healiostest.models.states.Error
import com.csosa.healiostest.models.states.ForecastDaysViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

internal class ForecastDaysViewModel(
    private val getForecastDaysUseCase: GetForecastDaysBaseUseCase
) : BaseViewModel() {

    // region Members

    var locationLat: Double = 0.0
    var locationLon: Double = 0.0
    var isConnected: Boolean = true

    private var getForecastDayJob: Job? = null

    val forecastDaysViewState: LiveData<ForecastDaysViewState>
        get() = _forecastDaysViewState

    private var _forecastDaysViewState = MutableLiveData<ForecastDaysViewState>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _forecastDaysViewState.value =
            _forecastDaysViewState.value?.copy(isLoading = false, error = Error(message))
    }

    // endregion

    // region Constructors

    init {
        _forecastDaysViewState.value =
            ForecastDaysViewState(isLoading = true, error = null, forecastDays = null)

    }

    // endregion

    // region Android API

    override fun onCleared() {
        super.onCleared()
        getForecastDayJob?.cancel()
    }

    // endregion

    // region Public API

    fun executegetForecastDays() {
        EspressoIdlingResource.increment()

        getForecastDayJob?.cancel()
        getForecastDayJob = launchCoroutine {
            delay(500)
            onForecastDaysLoading()

            val query = "$locationLat,$locationLon"

            val params = GetForecastDaysUseCaseInput(BuildConfig.API_KEY, query, isConnected)

            getForecastDaysUseCase(params).collect { forecastDays ->

                val forecastDaysPresentation = forecastDays.map { it.toPresentation() }
                onForecastDaysLoadingComplete(forecastDaysPresentation)
            }
        }
    }

    // endregion

    // region Private API

    private fun onForecastDaysLoading() {

        EspressoIdlingResource.increment()
        _forecastDaysViewState.value = _forecastDaysViewState.value?.copy(isLoading = true)
    }

    private fun onForecastDaysLoadingComplete(forecastDays: List<ForecastDayPresentation>) {

        EspressoIdlingResource.increment()
        _forecastDaysViewState.value =
            _forecastDaysViewState.value?.copy(isLoading = false, forecastDays = forecastDays)
    }

    // endregion
}
