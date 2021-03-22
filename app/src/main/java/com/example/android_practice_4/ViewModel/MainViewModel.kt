package com.example.android_practice_4.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import com.example.android_practice_4.repo.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _result = MutableLiveData<Result>()
    private val _pokemon = MutableLiveData<PokemonDetail>()

    val result : LiveData<Result>
        get() = _result

    val pokemon : LiveData<PokemonDetail>
        get() = _pokemon

    fun getNextPokemons(offset: String, limit: String) {
        val call = Repository.getNextPokemons(offset, limit)
        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                _result.postValue(response.body())
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("MainViewModel", "onFailure: ${t.message}")
            }

        })
    }

    fun getSinglePokemon(name: String) {
        val call = Repository.getSinglePokemon(name)
        call.enqueue(object : Callback<PokemonDetail> {
            override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                _pokemon.postValue(response.body())
            }

            override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                Log.d("MainViewModel", "onFailure: ${t.message}")
            }

        })
    }


    fun getPokemons() {
        val call = Repository.getPokemons()
        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    _result.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("MainViewModel", "onFailure: ${t.message}")
            }

        })
    }

}
