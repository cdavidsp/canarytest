package com.csosa.healiostest.data.local.mappers

import com.csosa.healiostest.data.local.models.ForecastDayEntity
import com.csosa.healiostest.domain.model.ForecastDay

internal fun ForecastDay.toEntity() : ForecastDayEntity {
    return ForecastDayEntity(
        id = this.id,
        lat = this.lat,
        lon = this.lon,

        locationName = this.locationName,
        locationCountry = this.locationCountry,

        avgtemp_c = this.avgtemp_c,
        date = this.date,
        maxtemp_c = this.maxtemp_c,
        mintemp_c = this.mintemp_c,
        maxwind_kph = this.maxwind_kph,
        totalprecip_mm = this.totalprecip_mm,
        avghumidity = this.avghumidity,

        conditionText = this.conditionText,
        conditionIcon = this.conditionIcon,

        sunrise = this.sunrise,
        sunset = this.sunset,
        moonrise = this.moonrise,
        moonset = this.moonset,
        moon_phase = this.moon_phase,
        moon_illumination = this.moon_illumination
    )
}
