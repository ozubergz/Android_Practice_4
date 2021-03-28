package com.example.android_practice_4.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_practice_4.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM result")
    fun getAll() : Flow<Result>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(result: Result)
}