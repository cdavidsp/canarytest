package com.csosa.healiostest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.csosa.healiostest.data.local.dao.ForecastDaysDao
import com.csosa.healiostest.data.local.models.ForecastDayEntity

@Database(entities = [ForecastDayEntity::class], version = 6, exportSchema = false)
abstract class HealiosDatabase : RoomDatabase() {
    abstract fun forecastDaysDao(): ForecastDaysDao
}
