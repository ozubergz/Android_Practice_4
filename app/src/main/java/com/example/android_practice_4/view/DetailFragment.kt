 package com.example.android_practice_4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.android_practice_4.ViewModel.MainViewModel
import com.example.android_practice_4.adapter.AbilityAdapter
import com.example.android_practice_4.adapter.StatAdapter
import com.example.android_practice_4.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.name

        viewModel.getSinglePokemon(name)

        viewModel.pokemon.observe(viewLifecycleOwner, {
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