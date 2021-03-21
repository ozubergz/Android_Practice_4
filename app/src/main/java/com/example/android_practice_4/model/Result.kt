package com.example.android_practice_4.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
