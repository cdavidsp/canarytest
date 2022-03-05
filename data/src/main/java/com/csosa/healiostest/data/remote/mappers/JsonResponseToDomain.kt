package com.csosa.healiostest.data.remote.mappers

import com.csosa.healiostest.data.remote.models.WAForecastDay
import com.csosa.healiostest.domain.model.ForecastDay

internal fun WAForecastDay.toDomain(): ForecastDay {
    return ForecastDay(
        id = 0,
        lat = 0.0,
        lon = 0.0,

        locationName = "",
        locationCountry = "",

        date = date,
        maxtemp_c = this.day?.maxtemp_c,
        mintemp_c = this.day?.mintemp_c,
        avgtemp_c = this.day?.avgtemp_c,
        maxwind_kph = this.day?.maxwind_kph,
        totalprecip_mm = this.day?.totalprecip_mm,
        avghumidity = this.day?.avghumidity,

        conditionText = this.day?.condition?.text,
        conditionIcon = this.day?.condition?.icon,

        sunrise = this.astro?.sunrise,
        sunset = this.astro?.sunset,
        moonrise = this.astro?.moonrise,
        moonset = this.astro?.moonset,
        moon_phase = this.astro?.moon_phase,
        moon_illumination = this.astro?.moon_illumination
    )
}