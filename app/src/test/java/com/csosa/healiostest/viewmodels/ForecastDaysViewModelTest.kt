package com.csosa.healiostest.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.csosa.healiostest.BaseViewModelTest
import com.csosa.healiostest.fakes.FakeGetForecastDaysUseCase
import com.csosa.healiostest.utils.UiState
import com.csosa.healiostest.viewmodel.ForecastDaysViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(manifest=Config.NONE, sdk = [Build.VERSION_CODES.P])
internal class ForecastDaysViewModelTest : BaseViewModelTest() {

    // region Members

    private lateinit var forecastDaysViewModel: ForecastDaysViewModel

    // endregion

    // region Tests

    @Test
    fun `should get all post`() {
        coroutineTestRule.dispatcher.runBlockingTest {

            prepareViewModel(UiState.SUCCESS)
            forecastDaysViewModel.executegetForecastDays()

            forecastDaysViewModel.forecastDaysViewState.observeForever { state ->

                if (state.isLoading) {

                    Truth.assertThat(state.forecastDays).isNull()
                    Truth.assertThat(state.error).isNull()
                } else {

                    Truth.assertThat(state.error).isNull()
                    Truth.assertThat(state.forecastDays?.size).isEqualTo(3)
                }
            }
        }
    }

    // endregion

    // region BaseViewModelTest

    override fun prepareViewModel(uiState: UiState) {

        val getForecastDaysUseCase = FakeGetForecastDaysUseCase(uiState)

        forecastDaysViewModel = ForecastDaysViewModel(getForecastDaysUseCase)

    }

    // endregion

}
