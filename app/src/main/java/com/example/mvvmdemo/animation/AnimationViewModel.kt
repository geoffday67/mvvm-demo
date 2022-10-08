package com.example.mvvmdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AnimationViewModel: ViewModel() {
    var big by mutableStateOf(false)
    var count by mutableStateOf(0)

    fun onSizeChange() {
        big = !big
    }

    fun onAnimationDone() {
        count++
    }
}