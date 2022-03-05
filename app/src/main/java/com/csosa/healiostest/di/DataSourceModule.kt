package com.csosa.healiostest.di

import androidx.room.Room
import com.csosa.healiostest.data.local.HealiosDatabase
import com.csosa.healiostest.data.local.dao.ForecastDaysDao
import com.csosa.healiostest.data.repository.ForecastDaysRepository
import com.csosa.healiostest.domain.repository.IForecastDaysRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {

    single<IForecastDaysRepository> { ForecastDaysRepository(forecastDaysDao = get(), apiService = get(), appPreferences = get()) }

    single {
        Room
            .databaseBuilder(androidContext(), HealiosDatabase::class.java, "helios_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { provideForecastDaysDao(db = get()) }
}

internal fun provideForecastDaysDao(db: HealiosDatabase): ForecastDaysDao = db.forecastDaysDao()