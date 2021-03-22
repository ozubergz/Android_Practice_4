package com.example.android_practice_4.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_practice_4.R
import com.example.android_practice_4.ViewModel.MainViewModel
import com.example.android_practice_4.adapter.PokemonAdapter
import com.example.android_practice_4.databinding.ActivityMainBinding
import com.example.android_practice_4.model.Pokemon

interface ClickListener {
    fun itemClick(name: String)
}

class MainActivity : AppCompatActivity(), ClickListener {

    private lateinit var nextUrl: String
    private lateinit var prevUrl: String
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPokemons()
        observers()
        listeners()
    }

    private fun observers() {
        viewModel.result.observe(this, Observer {
            binding.rvPokemons.apply {
                adapter = PokemonAdapter(it.results, this@MainActivity)
                layoutManager = GridLayoutManager(context, 4)
            }

            nextUrl = it.next.toString()
            prevUrl = it.previous.toString()

            if(it.previous == null) {
                binding.btnPrev.visibility = View.GONE
            } else {
                binding.btnPrev.visibility = View.VISIBLE
            }

            if(it.next == null) {
                binding.btnNext.visibility = View.GONE
            } else {
                binding.btnNext.visibility = View.VISIBLE
            }
        })
    }

    private fun listeners() {
        binding.btnNext.setOnClickListener {
            val offset = findRegex(nextUrl)
            viewModel.getNextPokemons(offset, "20")
        }

        binding.btnPrev.setOnClickListener {
            val offset = findRegex(prevUrl)
            viewModel.getNextPokemons(offset, "20")
        }
    }

    private fun findRegex(url: String): String {
        val matchResult = """=\d+""".toRegex().find(url)
        val first = matchResult!!.value
        return first.drop(1)
    }

    override fun itemClick(name: String) {
        val action = Intent(this, PokemonDetail::class.java)
        action.putExtra("Pokemon Name", name)
        startActivity(action)
    }
}