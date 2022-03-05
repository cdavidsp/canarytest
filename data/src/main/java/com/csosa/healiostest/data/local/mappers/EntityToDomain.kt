package com.csosa.healiostest.data.local.mappers

import com.csosa.healiostest.data.local.models.ForecastDayEntity
import com.csosa.healiostest.domain.model.ForecastDay

internal fun ForecastDayEntity.toDomain(): ForecastDay {
    return ForecastDay(
        id = id,
        lat = lat,
        lon = lon,

        locationName = this.locationName,
        locationCountry = this.locationCountry,

        avgtemp_c = this.avgtemp_c,
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
