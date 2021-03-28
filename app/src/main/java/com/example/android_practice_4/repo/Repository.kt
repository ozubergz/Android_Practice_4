package com.example.android_practice_4.repo

import com.example.android_practice_4.data.PokemonDB
import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val provideService: PokemonService,
    private val providePokemonDB: PokemonDB
) {

//    companion object {
//        private const val TIME_STAMP_KEY = "TIME_STAMP_KEY"
//    }

    suspend fun getPokemons() : Result {
        val result = provideService.getPokemons()
        if(result.isSuccessful) {
            result.body()?.let { providePokemonDB.pokemonDao().insertAll(it) }
        }

        return providePokemonDB.pokemonDao().getAll()

//        val sharedPref = context.getSharedPreferences("", Context.MODE_PRIVATE)
//        var savedTime = sharedPref.getLong(TIME_STAMP_KEY, 0)
//
//        if(savedTime > 0) {
//            sharedPref.edit().putLong(TIME_STAMP_KEY, System.currentTimeMillis()).apply()
//            savedTime = (savedTime / 1000) /60
//        }
//
//        val currentTime = (System.currentTimeMillis() / 1000) / 60
//        val diffTime = currentTime - savedTime
//
//        if(diffTime >= 10) {
//            val res = service.getPokemons()
//            res.body()?.let { pokemonDao.insertAll(it) }
//        }
//
//        return pokemonDao.getAll()
    }

    suspend fun getNextPokemons(offset: String, limit: String) : Response<Result> {
        return provideService.getNextPokemons(offset, limit)
    }

    suspend fun getSinglePokemon(name: String) : Response<PokemonDetail> {
        return provideService.getSinglePokemon(name)
    }


}