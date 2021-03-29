package com.example.android_practice_4.repo

import com.example.android_practice_4.data.PokemonDB
import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import com.example.android_practice_4.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val provideService: PokemonService,
    private val providePokemonDB: PokemonDB
) {

    private val pokemonDao = providePokemonDB.pokemonDao()

    fun getPokemons() = networkBoundResource(
            query =  { pokemonDao.getAll() },
            fetch = { provideService.getPokemons() },
            saveFetchResult = { pokemonDao.insertAll(it) }
    )

    suspend fun getNextPokemons(offset: String, limit: String) : Response<Result> {
        return provideService.getNextPokemons(offset, limit)
    }

    suspend fun getSinglePokemon(name: String) : Response<PokemonDetail> {
        return provideService.getSinglePokemon(name)
    }

}