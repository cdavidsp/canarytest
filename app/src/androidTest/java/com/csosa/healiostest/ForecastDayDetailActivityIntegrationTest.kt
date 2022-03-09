package com.csosa.healiostest

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.csosa.healiostest.activities.ForecastDayDetailActivity
import com.csosa.healiostest.commons.NavigationUtils
import com.csosa.healiostest.models.ForecastDayPresentation
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ForecastDayDetailActivityIntegrationTest : BaseTest() {

    @get:Rule
    var activityRule: ActivityTestRule<ForecastDayDetailActivity> =
        ActivityTestRule(ForecastDayDetailActivity::class.java, false, false)

    @Test
    fun shouldDisplayErrorOnLaunchWithDefaultId() {
        val intent = Intent()
        activityRule.launchActivity(intent)
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(activityRule.activity.resources.getString(R.string.error_loading_forecast_day_details))))
    }

    @Test
    fun shouldLoadDataOnLaunchWithValidForecastDayId() {
        val intent = Intent().putExtra(
            NavigationUtils.FORECAST_DAY_PARCEL_KEY,
            ForecastDayPresentation(
                id = 1,
                lat = 0.0,
                lon = 0.0,
                locationName = "Madrid",
                locationCountry = "",

                date = "",

                avgtemp_c  = 0f,
                maxtemp_c = 0f,
                mintemp_c = 0f,

                maxwind_kph = 0f,
                totalprecip_mm = 0f,
                avghumidity = 0f,

                conditionText = "",
                conditionIcon = "",

                sunrise = "",
                sunset = "",
                moonrise = "",
                moonset = "",
                moon_phase = "",
                moon_illumination = ""
            )
        )
        activityRule.launchActivity(intent)
            onView(withId(R.id.cityTV)).check(matches(withText("Madrid")))
    }

    @After
    override fun tearDown() {
        super.tearDown()
        activityRule.finishActivity()
    }

}
