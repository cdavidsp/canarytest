package com.csosa.healiostest.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecastdays")
data class ForecastDayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "lat") var lat: Double?,
    @ColumnInfo(name = "lon") var lon: Double?,


    @ColumnInfo(name = "locationName") var locationName: String?,
    @ColumnInfo(name = "locationCountry") var locationCountry: String?,


    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "avgtemp_c") val avgtemp_c: Float?,
    @ColumnInfo(name = "maxtemp_c") val maxtemp_c: Float?,
    @ColumnInfo(name = "mintemp_c") val mintemp_c: Float?,
    @ColumnInfo(name = "maxwind_kph") val maxwind_kph: Float?,
    @ColumnInfo(name = "totalprecip_mm") val totalprecip_mm: Float?,
    @ColumnInfo(name = "avghumidity") val avghumidity: Float?,

    @ColumnInfo(name = "conditionText") val conditionText: String?,
    @ColumnInfo(name = "conditionIcon") val conditionIcon: String?,

    @ColumnInfo(name = "sunrise") val sunrise: String?,
    @ColumnInfo(name = "sunset") val sunset: String?,
    @ColumnInfo(name = "moonrise") val moonrise: String?,
    @ColumnInfo(name = "moonset") val moonset: String?,
    @ColumnInfo(name = "moon_phase") val moon_phase: String?,
    @ColumnInfo(name = "moon_illumination") val moon_illumination: String?
)
