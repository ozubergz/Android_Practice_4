package com.example.android_practice_4.repo

import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    fun getPokemons() : Call<Result>

    @GET("pokemon")
    fun getNextPokemons(
            @Query("offset") offset: String,
            @Query("limit") limit: String
    ) : Call<Result>

    @GET("pokemon/{name}")
    fun getSinglePokemon(@Path("name") name: String) : Call<PokemonDetail>
}