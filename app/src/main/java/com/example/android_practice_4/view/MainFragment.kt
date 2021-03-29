package com.example.android_practice_4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_practice_4.viewmodel.MainViewModel
import com.example.android_practice_4.adapter.PokemonAdapter
import com.example.android_practice_4.databinding.FragmentMainBinding
import com.example.android_practice_4.util.Resource
import dagger.hilt.android.AndroidEntryPoint


interface ClickListener {
    fun itemClick(name: String)
}

@AndroidEntryPoint
class MainFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) = FragmentMainBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewModel.getPokemons.observe(viewLifecycleOwner, { result ->
                if (result.data != null) {
                    val data = result.data

                    rvPokemons.apply {
                        adapter = PokemonAdapter(data.results, this@MainFragment)
                        layoutManager = GridLayoutManager(context, 4)
                    }

                    btnPrev.isVisible = data.previous != null
                    btnNext.isVisible = data.next != null

                    listeners(btnNext, data.next.toString())
                    listeners(btnPrev, data.previous.toString())

                    progressBar.isVisible = result is Resource.Loading && data.results.isNullOrEmpty()
                    tvError.isVisible = result is Resource.Error && data.results.isNullOrEmpty()
                    tvError.text = result.error?.localizedMessage
                }
            })
        }

        observers()
    }

    private fun observers() {
        binding.apply {

            viewModel.result.observe(viewLifecycleOwner, Observer {
                rvPokemons.apply {
                    adapter = PokemonAdapter(it.results, this@MainFragment)
                    layoutManager = GridLayoutManager(context, 4)
                }

                btnPrev.isVisible = it.previous != null
                btnNext.isVisible = it.next != null

                listeners(btnNext, it.next.toString())
                listeners(btnPrev, it.previous.toString())
            })
        }
    }

    private fun listeners(btn: Button, url: String) {
        btn.setOnClickListener {
            viewModel.getNextPokemons(findRegex(url), "20")
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