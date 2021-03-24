package com.example.android_practice_4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass


@Entity(tableName = "result")
@JsonClass(generateAdapter = true)
data class Result (
    @PrimaryKey
    val id : Int = 0,
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
