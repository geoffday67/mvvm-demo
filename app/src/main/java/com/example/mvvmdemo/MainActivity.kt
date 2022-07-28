package com.example.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mvvmdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val model: MainViewModel by viewModels()

        model.name.observe(
            this
        ) {
            binding.name.text = "Hello ${it ?: "stranger"}"
        }

        model.geoff.observe(
            this
        ) {
            binding.geoff.text = "Geoff is ${it ?: "not sure"}"
        }

        binding.submit.setOnClickListener {
            model.handleSubmit()
        }
    }
}