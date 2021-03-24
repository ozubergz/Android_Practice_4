package com.example.android_practice_4.data

import android.content.Context
import androidx.room.*
import com.example.android_practice_4.model.Result

@Database(entities = [Result::class], version = 1)
@TypeConverters(Converters::class)
abstract class PokemonDB : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDB? = null

        fun getDataBase(context: Context) : PokemonDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDB::class.java,
                    "pokemon_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}