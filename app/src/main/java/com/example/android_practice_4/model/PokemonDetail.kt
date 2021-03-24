package com.example.android_practice_4.model

import androidx.room.Entity
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class PokemonDetail(
        val id: Int,
        val order: Int,
        val name: String,
        val height: Int,
        val weight: Int,
        val stats: List<Stat>,
        val abilities: List<Ability>
)
