package com.csosa.healiostest.data.repository

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.csosa.healiostest.data.BaseTest
import com.csosa.healiostest.domain.repository.IForecastDaysRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
internal class ForecastDaysRepositoryTest : BaseTest() {

    private lateinit var forecastDaysRepository: IForecastDaysRepository

    @Before
    override fun setup() {
        super.setup()
        forecastDaysRepository = ForecastDaysRepository(heliosApiService, forecastDaysDao)
    }

    @Test
    fun `when forecast days are requested and the device has internet connection, then return data should from api `() {
        runBlocking(Dispatchers.IO) {

            val forecastFlow = forecastDaysRepository.getForecastDays("", "", true)
            val numberOfDays = 3 // in forecast.json

            forecastFlow.collect { forecastDays ->
                Truth.assertThat(forecastDays.size).isEqualTo(numberOfDays)
            }
        }
    }

    @Test
    fun `when forecast days are requested and the device hasen't internet connection, then return data should from db `() {
        runBlocking(Dispatchers.IO) {

            val forecastDaysConnectedFlow = forecastDaysRepository.getForecastDays("", "", true)
            val numberOfDaysforConnected = 3 // in forecast.json

            forecastDaysConnectedFlow.collect { forecastDays ->
                Truth.assertThat(forecastDays.size).isEqualTo(numberOfDaysforConnected)
            }


            forecastDaysDao.deleteById(1)


            val forecastDaysNotConnectedFlow = forecastDaysRepository.getForecastDays("", "", false)
            val numberOfDaysforNotConnected = 2 // in forecast.json

            forecastDaysNotConnectedFlow.collect { forecastDays ->
                Truth.assertThat(forecastDays.size).isEqualTo(numberOfDaysforNotConnected)
            }

        }
    }

}
