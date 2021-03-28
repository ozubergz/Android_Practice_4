package com.example.android_practice_4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_practice_4.model.PokemonDetail
import com.example.android_practice_4.model.Result
import com.example.android_practice_4.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _result = MutableLiveData<Result>()
    private val _pokemon = MutableLiveData<PokemonDetail>()

    val result : LiveData<Result>
        get() = _result

    val pokemon : LiveData<PokemonDetail>
        get() = _pokemon

    fun getNextPokemons(offset: String, limit: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.getNextPokemons(offset, limit)
            _result.postValue(res.body())
        }
    }

    fun getSinglePokemon(name: String) {
        viewModelScope.launch {
            val res = repository.getSinglePokemon(name)
            _pokemon.postValue(res.body())
        }
    }

    fun getPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPokemons()
            _result.postValue(result)
        }
    }

}
