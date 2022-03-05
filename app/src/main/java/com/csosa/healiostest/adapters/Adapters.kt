package com.csosa.healiostest.adapters

import com.csosa.healiostest.R
import com.csosa.healiostest.models.ForecastDayPresentation
import me.ibrahimyilmaz.kiel.adapterOf

@Suppress("NOTHING_TO_INLINE")
internal inline fun createForecastDaysAdapter(noinline onClick: (ForecastDayPresentation) -> Unit) =
    adapterOf<ForecastDayPresentation> {
        diff(
            areItemsTheSame = { old, new -> old === new },
            areContentsTheSame = { old, new ->
                old.id == new.id
            }
        )
        register(
            layoutResource = R.layout.item_forecast_day,
            viewHolder = ::ForecastDayViewHolder,
            onBindViewHolder = { vh, _, forecastDay ->
                vh.binding.forecastDay = forecastDay
                vh.binding.buttonViewMore.setOnClickListener {
                    onClick(forecastDay)
                }
            }
        )
    }
