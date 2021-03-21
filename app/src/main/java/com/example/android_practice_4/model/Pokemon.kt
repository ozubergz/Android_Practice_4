package com.example.android_practice_4.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    val name: String,
    val url: String
)