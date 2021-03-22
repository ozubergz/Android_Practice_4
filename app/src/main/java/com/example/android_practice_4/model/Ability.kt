package com.example.android_practice_4.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ability(
        val ability: AbilityName,
        @Json(name = "is_hidden")
        val isHidden: Boolean,
        val slot: Int
)
