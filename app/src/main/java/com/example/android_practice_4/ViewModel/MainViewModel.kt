package com.example.android_practice_4.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import com.example.android_practice_4.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        viewModelScope.launch(Dispatchers.IO) {
            val res = Repository.getNextPokemons(offset, limit)
            _result.postValue(res.body())
        }
    }

    fun getSinglePokemon(name: String) {
        viewModelScope.launch {
            val res = Repository.getSinglePokemon(name)
            _pokemon.postValue(res.body())
        }
    }

    fun getPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = Repository.getPokemons()
            _result.postValue(res.body())
        }
    }

}
