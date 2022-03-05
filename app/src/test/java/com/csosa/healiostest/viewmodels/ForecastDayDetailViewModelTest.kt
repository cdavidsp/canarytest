package com.csosa.healiostest.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.csosa.healiostest.BaseViewModelTest
import com.csosa.healiostest.mappers.toPresentation
import com.csosa.healiostest.utils.Data
import com.csosa.healiostest.utils.UiState
import com.csosa.healiostest.viewmodel.ForecastDayDetailViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
@ExperimentalCoroutinesApi
internal class ForecastDayDetailViewModelTest : BaseViewModelTest() {

    // region Members

    private lateinit var forecastDayDetailViewModel: ForecastDayDetailViewModel

    // endregion

    // region Tests

    @Test
    fun `given a ForecastDayPresentation then get Forecast day details`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)
            val forecastDayId = 1L
            val forecastDayPresentation = Data.forecastDays.firstOrNull { it.id == forecastDayId }!!.toPresentation()

            forecastDayDetailViewModel.getForecastDayDetails(forecastDayPresentation)

            forecastDayDetailViewModel.detailViewState.observeForever { detailViewState ->

                if (detailViewState.isComplete) {

                    Truth.assertThat(detailViewState.error).isNull()
                    Truth.assertThat(detailViewState.info).isNotNull()
                } else {

                    Truth.assertThat(detailViewState.error).isNull()
                    Truth.assertThat(detailViewState.info).isNull()
                }
            }
        }
    }


    // endregion

    // region BaseViewModelTest

    override fun prepareViewModel(uiState: UiState) {

        forecastDayDetailViewModel =
            ForecastDayDetailViewModel()
    }

    // endregion

    // region Helpers

    @After
    fun clear() {

       // Data.users.clear()
       // Data.comments.clear()
    }

    // endregion
}
