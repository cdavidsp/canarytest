package com.csosa.healiostest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csosa.healiostest.data.local.models.ForecastDayEntity


@Dao
interface ForecastDaysDao {

    @Query("SELECT * FROM forecastdays")
    suspend fun getAll(): List<ForecastDayEntity>

    @Query("SELECT COUNT(*) FROM forecastdays")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<ForecastDayEntity?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: ForecastDayEntity?)

    @Query("DELETE FROM forecastdays")
    suspend fun deleteAll()

    @Query("DELETE FROM forecastdays WHERE id=:id")
    suspend fun deleteById(id: Int)
}
