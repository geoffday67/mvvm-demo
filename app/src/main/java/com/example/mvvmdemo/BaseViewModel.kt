package com.example.mvvmdemo

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    open val helpId: Int = R.string.help_general
}