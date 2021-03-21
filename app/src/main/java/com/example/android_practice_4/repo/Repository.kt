package com.example.android_practice_4.repo

import com.example.android_practice_4.model.Pokemon
import com.example.android_practice_4.model.Result
import retrofit2.Call

object Repository {

    val service = RetrofitInstance.pokemonService

    fun getPokemons() : Call<Result> {
        return service.getPokemons()
    }
}