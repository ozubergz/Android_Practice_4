package com.example.android_practice_4.repo

import com.example.android_practice_4.model.Result
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon")
    fun getPokemons() : Call<Result>
}