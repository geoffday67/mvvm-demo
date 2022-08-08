package com.example.mvvmdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MainViewModel by viewModels()

        model.name.observe(this) {
            findViewById<TextView>(R.id.name).text = "Hello ${it ?: "stranger"}"
        }

        model.geoff.observe(this) {
            findViewById<TextView>(R.id.geoff).text = "Geoff is ${it ?: "not sure"}"
        }

        findViewById<Button>(R.id.submit).setOnClickListener {
            model.delay()
        }
    }
}