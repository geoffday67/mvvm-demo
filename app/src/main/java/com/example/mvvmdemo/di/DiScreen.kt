package com.example.mvvmdemo.di

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

@Composable
fun DiScreenPart1(
    //diViewModel: DiViewModel =  remember { DiViewModel() },
    diViewModel: DiViewModel = getViewModel(),
) {
    Column(
        Modifier.padding(24.dp)
    ) {
        Text("Part 1")
        Text(
            modifier = Modifier.testTag("id"),
            text = diViewModel.message,
        )
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
        Text(
            modifier = Modifier.testTag("id"),
            text = diViewModel.message,
        )
    }
}
