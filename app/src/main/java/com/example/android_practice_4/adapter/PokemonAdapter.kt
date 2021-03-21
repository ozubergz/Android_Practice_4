package com.example.android_practice_4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_practice_4.databinding.PokemonItemBinding
import com.example.android_practice_4.model.Pokemon

class PokemonAdapter(private val dataSet: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(
        private val binding: PokemonItemBinding,
        private val dataSet: List<Pokemon>) : RecyclerView.ViewHolder(binding.root) {
            fun bind(position: Int) {
                val pokemon = dataSet[position]
                binding.pokemonName.text = pokemon.name

                val matchResult = """\/\d+""".toRegex().find(pokemon.url)
                val value = matchResult?.value
                val id = value?.drop(1)

                val sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                Glide.with(this.itemView).load(sprite).into(binding.pokemonImage)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(view, dataSet)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}