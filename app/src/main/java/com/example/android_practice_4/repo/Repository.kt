package com.example.android_practice_4.repo

import com.example.android_practice_4.model.Pokemon
import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import retrofit2.Call
import retrofit2.Response

object Repository {

    private val service = RetrofitInstance.pokemonService

    suspend fun getPokemons() : Response<Result> {
        return service.getPokemons()
    }

    suspend fun getNextPokemons(offset: String, limit: String) : Response<Result> {
        return service.getNextPokemons(offset, limit)
    }

    suspend fun getSinglePokemon(name: String) : Response<PokemonDetail> {
        return service.getSinglePokemon(name)
    }
}