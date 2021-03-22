package com.example.android_practice_4.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stat(
        @Json(name = "base_stat")
        val baseStat: String,
        val effort: Int,
        val stat: StatName
)
