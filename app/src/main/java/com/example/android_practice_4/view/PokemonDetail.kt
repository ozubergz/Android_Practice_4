package com.example.android_practice_4.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.android_practice_4.ViewModel.MainViewModel
import com.example.android_practice_4.adapter.AbilityAdapter
import com.example.android_practice_4.adapter.StatAdapter
import com.example.android_practice_4.databinding.PokemonDetailBinding
import com.example.android_practice_4.databinding.PokemonItemBinding

class PokemonDetail : AppCompatActivity() {

    private lateinit var binding: PokemonDetailBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("Pokemon Name")
        viewModel.getSinglePokemon(name!!)

        viewModel.pokemon.observe(this, {
            val sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png"
            Glide.with(this).load(sprite).into(binding.ivSprite)

            binding.tvName.text = "name: ${it.name}"
            binding.tvOrder.text = "order: ${it.order}"
            binding.tvHeight.text = "height: ${it.height}"
            binding.tvWeight.text = "weight: ${it.weight}"

            // ListView for Abilities
            binding.lvAbilities.divider = null
            binding.lvAbilities.adapter = AbilityAdapter(it.abilities)

            // ListView for Stats
            binding.lvAbilities.divider = null
            binding.lvStats.adapter = StatAdapter(it.stats)

        })
    }

}