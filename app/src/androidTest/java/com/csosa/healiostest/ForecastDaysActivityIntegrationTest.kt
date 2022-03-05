package com.csosa.healiostest

import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.csosa.healiostest.activities.ForecastDaysActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
internal class ForecastDaysActivityIntegrationTest : BaseTest() {

    @get:Rule
    var activityRule: ActivityTestRule<ForecastDaysActivity> =
        ActivityTestRule(ForecastDaysActivity::class.java, false, false)

    @get:Rule
    val permissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_NETWORK_STATE
    )
    @get:Rule
    val intentsTestRule = IntentsTestRule(ForecastDaysActivity::class.java)

    @Before
    fun setUp() {
        assumeTrue("This test needs to run on a device with Android 23 or above", Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
    }
    @Test
    fun shouldNavigateToPostDetailOnItemClickFromList() {


        ActivityScenario.launch(ForecastDaysActivity::class.java)

        onView(allOf(withId(R.id.button_view_more), isDisplayed())).perform(click());

        onView(withId(R.id.cityTV)).check(ViewAssertions.matches(withText("Madrid")))

    }

    companion object {
        private const val FORECAST_DAY_DETAIL_ACTIVITY_COMPONENT =
            "com.csosa.healiostest.activities.ForecastDayDetailActivity"
    }
}