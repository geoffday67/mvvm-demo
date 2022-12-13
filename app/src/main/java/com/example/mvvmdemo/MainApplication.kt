package com.example.mvvmdemo

import android.app.Application
import com.example.mvvmdemo.animation.AnimationViewModel
import com.example.mvvmdemo.di.DiViewModel
import com.example.mvvmdemo.help.HelpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                module {
                    viewModelOf(::DiViewModel)
                    viewModelOf(::HelpViewModel)
                    viewModelOf(::ApiViewModel)
                    viewModelOf(::AnimationViewModel)
                    viewModel { params -> MainViewModel(params.get()) }
                },
            )
        }
    }
}