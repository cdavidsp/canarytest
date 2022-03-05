package com.csosa.healiostest.data.remote.models

data class WALocation(
    val name: String?,
    val region: String?,
    val country: String?,

    val lat: Double?,
    val lon: Double?,

    val tz_id: String?,
    val localtime: String?
)
