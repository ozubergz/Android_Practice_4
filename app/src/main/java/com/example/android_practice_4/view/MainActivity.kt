package com.example.android_practice_4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_practice_4.R
import com.example.android_practice_4.ViewModel.MainViewModel
import com.example.android_practice_4.adapter.PokemonAdapter
import com.example.android_practice_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPokemons()

        viewModel.result.observe(this, Observer {
            binding.rvPokemons.apply {
                adapter = PokemonAdapter(it.results)
                layoutManager = LinearLayoutManager(context)
            }
        })

    }
}