package com.example.android_practice_4.repo

import com.example.android_practice_4.model.Pokemon
import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import retrofit2.Call

object Repository {

    private val service = RetrofitInstance.pokemonService

    fun getPokemons() : Call<Result> {
        return service.getPokemons()
    }

    fun getNextPokemons(offset: String, limit: String) : Call<Result> {
        return service.getNextPokemons(offset, limit)
    }

    fun getSinglePokemon(name: String) : Call<PokemonDetail> {
        return service.getSinglePokemon(name)
    }
}