package com.example.mvvmdemo.di

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

/*
This version creates two instances of the ViewModel but using DI creates only one per screen.
 */

@Composable
fun DiScreenPart1(
    //diViewModel: DiViewModel =  remember { DiViewModel() },
    diViewModel: DiViewModel = getViewModel(),
) {
    Column(
        Modifier.padding(24.dp)
    ) {
        Text("Part 1")
        Text(diViewModel.message)
    }
}

@Composable
fun DiScreenPart2(
    //diViewModel: DiViewModel =  remember { DiViewModel() },
    diViewModel: DiViewModel = getViewModel(),
) {
    Column(
        Modifier.padding(24.dp)
    ) {
        Text("Part 2")
        Text(diViewModel.message)
    }
}
