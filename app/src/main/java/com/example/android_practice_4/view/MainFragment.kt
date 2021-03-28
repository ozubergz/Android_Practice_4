package com.example.android_practice_4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_practice_4.viewmodel.MainViewModel
import com.example.android_practice_4.adapter.PokemonAdapter
import com.example.android_practice_4.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint


interface ClickListener {
    fun itemClick(name: String)
}

@AndroidEntryPoint
class MainFragment : Fragment(), ClickListener {

    private lateinit var nextUrl: String
    private lateinit var prevUrl: String
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) = FragmentMainBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPokemons()
        observers()
        listeners()
    }

    private fun observers() {
        viewModel.result.observe(viewLifecycleOwner, Observer {
            binding.rvPokemons.apply {
                adapter = PokemonAdapter(it.results, this@MainFragment)
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
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(name)
        findNavController().navigate(action)
    }

}