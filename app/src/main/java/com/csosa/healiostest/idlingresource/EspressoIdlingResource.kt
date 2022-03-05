package com.csosa.healiostest.idlingresource

import android.util.Log
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        Log.i("EspressoIdlingResource", "increment")
        countingIdlingResource.increment()
    }

    fun decrement() {
        Log.i("EspressoIdlingResource", "decrement")
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}
