package com.example.android_practice_4.repo

import android.content.Context
import com.example.android_practice_4.data.PokemonDB
import com.example.android_practice_4.model.Pokemon
import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

object Repository {

    private const val TIME_STAMP_KEY = "TIME_STAMP_KEY"
    private val service = RetrofitInstance.pokemonService

    suspend fun getPokemons(context: Context) : Result {

        val pokemonDao = PokemonDB.getDataBase(context).pokemonDao()
        val sharedPref = context.getSharedPreferences("", Context.MODE_PRIVATE)
        var savedTime = sharedPref.getLong(TIME_STAMP_KEY, 0)

        if(savedTime > 0) {
            sharedPref.edit().putLong(TIME_STAMP_KEY, System.currentTimeMillis()).apply()
            savedTime = (savedTime / 1000) /60
        }

        val currentTime = (System.currentTimeMillis() / 1000) / 60
        val diffTime = currentTime - savedTime

        if(diffTime >= 10) {
            val res = service.getPokemons()
            res.body()?.let { pokemonDao.insertAll(it) }
        }

        return pokemonDao.getAll()
    }

    suspend fun getNextPokemons(offset: String, limit: String) : Response<Result> {
        return service.getNextPokemons(offset, limit)
    }

    suspend fun getSinglePokemon(name: String) : Response<PokemonDetail> {
        return service.getSinglePokemon(name)
    }


}