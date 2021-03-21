package com.example.android_practice_4.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_practice_4.model.Result
import com.example.android_practice_4.repo.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _result = MutableLiveData<Result>()

    val result : LiveData<Result>
        get() = _result


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