package com.csosa.healiostest.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.csosa.healiostest.R
import com.csosa.healiostest.base.BaseActivity
import com.csosa.healiostest.commons.*
import com.csosa.healiostest.databinding.ActivityForecastDayDetailBinding
import com.csosa.healiostest.idlingresource.EspressoIdlingResource
import com.csosa.healiostest.models.ForecastDayPresentation
import com.csosa.healiostest.viewmodel.ForecastDayDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ForecastDayDetailActivity : BaseActivity(), IForecastDayDetailBinder {

    // region Members

    private val forecastDayDetailViewModel by viewModel<ForecastDayDetailViewModel>()

    private lateinit var binding: ActivityForecastDayDetailBinding

    // endregion

    // region Android API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forecast_day_detail)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val forecastDayPresentation =
            intent.getParcelableExtra<ForecastDayPresentation>(NavigationUtils.FORECAST_DAY_PARCEL_KEY)

        observeDetailViewState()

        if (forecastDayPresentation == null) {
            forecastDayDetailViewModel
                .displayForecastDayError(R.string.error_loading_forecast_day_details)
        } else {

            forecastDayDetailViewModel.getForecastDayDetails(forecastDayPresentation)
        }

    }

    // endregion

    // region Private API

    private fun observeDetailViewState() {

        forecastDayDetailViewModel.detailViewState.observe(this, Observer {
            bindForecastDayDetail(it.info)

            EspressoIdlingResource.decrement()

            it.error?.let { e ->
                onError(resources.getString(e.message))
            }
            if (it.isComplete) {
                showSnackBar(
                    binding.forecastDayDetailsLayout,
                    getString(R.string.info_loading_complete)
                )
            }
        })
    }

    private fun onError(message: String) {
        EspressoIdlingResource.decrement()

        showSnackBar(binding.forecastDayDetailsLayout, message, isError = true)
    }


    // endregion

    // region IForecastDayDetailsBinder

    override fun bindForecastDayDetail(forecastDay: ForecastDayPresentation?) {
        supportActionBar?.title = getString(R.string.title_list_forecast_days)
        binding.infoLayout.forecastDay = forecastDay
    }

    // endregion

}
