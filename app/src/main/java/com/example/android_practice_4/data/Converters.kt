package com.example.android_practice_4.data

import androidx.room.TypeConverter
import com.example.android_practice_4.model.Pokemon
import com.squareup.moshi.Moshi
import com.example.android_practice_4.model.Result
import com.squareup.moshi.Types

class Converters {

    @TypeConverter
    fun resultToString(pokemon: List<Pokemon>) : String {
        val type = Types.newParameterizedType(List::class.java, Pokemon::class.java)
        val adapter = Moshi.Builder().build().adapter<List<Pokemon>>(type)
        return adapter.toJson(pokemon)
    }

    @TypeConverter
    fun stringToResult(json: String) : List<Pokemon>? {
        val type = Types.newParameterizedType(List::class.java, Pokemon::class.java)
        val adapter = Moshi.Builder().build().adapter<List<Pokemon>>(type)
        return adapter.fromJson(json)
    }
}