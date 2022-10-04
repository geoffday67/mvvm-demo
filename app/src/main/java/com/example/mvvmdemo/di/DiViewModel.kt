package com.example.mvvmdemo.di

import androidx.lifecycle.ViewModel

class DiViewModel : ViewModel() {
    val message = "Injected text ID ${System.identityHashCode(this)}"
}
