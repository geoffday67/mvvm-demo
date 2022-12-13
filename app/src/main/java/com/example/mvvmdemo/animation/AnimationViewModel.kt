package com.example.mvvmdemo.animation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.BaseViewModel
import com.example.mvvmdemo.R

class AnimationViewModel: BaseViewModel() {
    var big by mutableStateOf(false)
    var count by mutableStateOf(0)

    override val helpId = R.string.help_animation

    fun onSizeChange() {
        big = !big
    }

    fun onAnimationDone() {
        count++
    }
}