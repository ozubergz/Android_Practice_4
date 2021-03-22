package com.example.android_practice_4.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatName(
        val name: String
)
