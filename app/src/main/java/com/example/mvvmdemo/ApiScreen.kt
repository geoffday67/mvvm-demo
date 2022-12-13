package com.example.mvvmdemo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun ApiScreen(
    viewModel: ApiViewModel,
) {
    LazyColumn() {
        items(viewModel.forces) {
            Text(it.name)
        }
    }
}