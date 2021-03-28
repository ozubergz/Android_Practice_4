package com.example.android_practice_4.repo

import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemons() : Result

    @GET("pokemon")
    suspend fun getNextPokemons(
            @Query("offset") offset: String,
            @Query("limit") limit: String
    ) : Response<Result>

    @GET("pokemon/{name}")
    suspend fun getSinglePokemon(
            @Path("name") name: String
    ) : Response<PokemonDetail>
}