package com.csosa.healiostest.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.csosa.healiostest.R
import com.csosa.healiostest.adapters.createForecastDaysAdapter
import com.csosa.healiostest.base.BaseActivity
import com.csosa.healiostest.commons.*
import com.csosa.healiostest.databinding.ActivityForecastDaysBinding
import com.csosa.healiostest.idlingresource.EspressoIdlingResource
import com.csosa.healiostest.models.ForecastDayPresentation
import com.csosa.healiostest.models.states.ForecastDaysViewState
import com.csosa.healiostest.viewmodel.ForecastDaysViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val TAG = "ForecastDaysActivity"

internal class ForecastDaysActivity : BaseActivity() {

    // region Members


    private val forecastDaysViewModel by viewModel<ForecastDaysViewModel>()

    private lateinit var binding: ActivityForecastDaysBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val forecastDaysAdapter = createForecastDaysAdapter {

        startActivity<ForecastDayDetailActivity> {
            putExtra(NavigationUtils.FORECAST_DAY_PARCEL_KEY, it)
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    // endregion

    // region Android API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forecast_days)
        configSupportActionBar()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        observeForecastDaysViewState()


    }


    override fun onResume() {
        super.onResume()

        Log.i(TAG, "onResume")

        handleForecastDaysLoading(ForecastDaysViewState(true, null, null))

        forecastDaysViewModel.isConnected = isNetworkAvailable(this)

        checkLocationPermission()

    }


    private fun checkLocationPermission() {


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                MY_PERMISSIONS_REQUEST_LOCATION
            )

        } else {

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    Log.i(TAG, "executegetForecastDays")

                    if (location != null) {
                        forecastDaysViewModel.locationLat = location.latitude
                        forecastDaysViewModel.locationLon = location.longitude
                    } else { // for testing purpose

                        forecastDaysViewModel.locationLat = 40.416775
                        forecastDaysViewModel.locationLon = -3.703790
                    }

                    forecastDaysViewModel.executegetForecastDays()
                }
        }


    }


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->

                    Log.i(TAG, "executegetForecastDays2")

                    // Got last known location. In some rare situations this can be null.
                    forecastDaysViewModel.locationLat = location?.latitude!!
                    forecastDaysViewModel.locationLon = location.longitude
                    forecastDaysViewModel.executegetForecastDays()
                }

        } else {
            showSnackBar(
                binding.forecastDaysViewPager2,
                "This app must have permissions for get Location",
                isError = true
            )
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            startActivity<SettingsActivity>()
            true
        } else super.onOptionsItemSelected(item)
    }

    // endregion

    // region Private API

    private fun configSupportActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.title_list_forecast_days)
    }

    private fun observeForecastDaysViewState() {
        forecastDaysViewModel.forecastDaysViewState.observe(this, Observer { state ->


            if (!state.isLoading) {

                state.forecastDays?.let { forecastDays ->
                    if (forecastDays.isNotEmpty()) {
                        handleForecastDays(forecastDays)
                    } else {
                        binding.noForecastDaysTextView.show()
                    }
                }
            }

            handleForecastDaysLoading(state)

            handleForecastDaysError(state)
        })
    }

    private fun handleForecastDaysLoading(state: ForecastDaysViewState) {

        if (state.isLoading) {
            binding.forecastDaysViewPager2.remove()
            binding.forecastDaysProgressBar.show()
        } else {
            binding.forecastDaysProgressBar.remove()
            binding.forecastDaysViewPager2.show()
            EspressoIdlingResource.decrement()
        }
    }

    private fun handleForecastDays(forecastDays: List<ForecastDayPresentation>) {
        Log.i(TAG, "handleForecastDays")
        binding.forecastDaysViewPager2.apply {
            adapter = forecastDaysAdapter.apply {
                submitList(forecastDays)
                EspressoIdlingResource.decrement()

            }
        }
    }

    private fun handleForecastDaysError(state: ForecastDaysViewState) {
        EspressoIdlingResource.decrement()
        state.error?.run {
            showSnackBar(
                binding.forecastDaysViewPager2,
                getString(this.message),
                isError = true
            )
        }
    }


    // endregion

    companion object {
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }

}
