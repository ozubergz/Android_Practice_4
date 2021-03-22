package com.example.android_practice_4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.android_practice_4.databinding.StatItemBinding
import com.example.android_practice_4.model.Stat

class StatAdapter(private val data: List<Stat>) : BaseAdapter() {
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
        val binding = StatItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        val item = getItem(position) as Stat
        binding.tvStat.text = "${item.stat.name}: ${item.baseStat}"
        return binding.root
    }

}