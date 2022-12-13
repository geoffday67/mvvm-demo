package com.example.mvvmdemo.help

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mvvmdemo.help.HelpViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HelpScreen(
    @StringRes id: Int,
) {
    Text(stringResource(id))
}