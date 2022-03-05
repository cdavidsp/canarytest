package com.csosa.healiostest.data.util

import java.net.InetAddress

@Suppress("NOTHING_TO_INLINE")
internal inline fun isInternetAvailable(): Boolean {
        return try {
            val ipAddress: InetAddress = InetAddress.getByName("google.com")
            !ipAddress.equals("")
        } catch (e: Exception) {
            false
        }
    }
