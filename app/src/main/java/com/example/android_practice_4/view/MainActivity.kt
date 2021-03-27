package com.example.android_practice_4.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_practice_4.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}