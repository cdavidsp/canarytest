package com.csosa.healiostest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.csosa.healiostest.commons.ExceptionHandler
import com.csosa.healiostest.idlingresource.EspressoIdlingResource
import com.csosa.healiostest.models.ForecastDayPresentation
import com.csosa.healiostest.models.states.ForecastDayDetailsViewState
import com.csosa.healiostest.models.states.Error
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job


internal class ForecastDayDetailViewModel() : BaseViewModel() {

    // region Members

    private var forecastDayDetailsJob: Job? = null

    val detailViewState: LiveData<ForecastDayDetailsViewState>
        get() = _detailViewState

    private var _detailViewState = MutableLiveData<ForecastDayDetailsViewState>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _detailViewState.value = _detailViewState.value?.copy(error = Error(message))
    }

    // endregion

    // region Constructor

    init {
        _detailViewState.value =
            ForecastDayDetailsViewState(
                isComplete = false,
                error = null,
                info = null
            )
    }

    // endregion

    // region Android API

    override fun onCleared() {
        super.onCleared()
        forecastDayDetailsJob?.cancel()
    }

    // endregion

    // region Public API

    fun getForecastDayDetails(forecastDay: ForecastDayPresentation, isRetry: Boolean = false) {

        EspressoIdlingResource.increment()
        _detailViewState.value = _detailViewState.value?.copy(info = forecastDay, isComplete = true)
        if (isRetry) {
            _detailViewState.value = _detailViewState.value?.copy(error = null)
        }

    }

    fun displayForecastDayError(message: Int) {

        EspressoIdlingResource.increment()
        _detailViewState.value = _detailViewState.value?.copy(error = Error(message))
    }

    // endregion
}

