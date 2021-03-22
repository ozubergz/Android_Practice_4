package com.example.android_practice_4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.android_practice_4.databinding.AbilityItemBinding
import com.example.android_practice_4.model.Ability

class AbilityAdapter(private val data: List<Ability>) : BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun isEnabled(position: Int): Boolean {
        return false
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = AbilityItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        val data = getItem(position) as Ability
        binding.tvName.text = data.ability.name
        return binding.root
    }
}