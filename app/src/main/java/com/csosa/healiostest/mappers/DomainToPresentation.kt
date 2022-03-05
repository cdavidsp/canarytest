package com.csosa.healiostest.mappers

import com.csosa.healiostest.domain.model.ForecastDay
import com.csosa.healiostest.models.ForecastDayPresentation

internal fun ForecastDay.toPresentation(): ForecastDayPresentation {
    return ForecastDayPresentation(
        id = id,
        lat = lat,
        lon = lon,
        locationName = locationName,
        locationCountry = locationCountry,

        avgtemp_c = avgtemp_c,
        date = date,
        maxtemp_c = maxtemp_c,
        mintemp_c = mintemp_c,
        maxwind_kph = maxwind_kph,
        totalprecip_mm = totalprecip_mm,
        avghumidity = avghumidity,

        conditionText = conditionText,
        conditionIcon = conditionIcon,

        sunrise = sunrise,
        sunset = sunset,
        moonrise = moonrise,
        moonset = moonset,
        moon_phase = moon_phase,
        moon_illumination = moon_illumination
    )
}
