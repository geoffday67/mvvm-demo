package com.example.mvvmdemo

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmdemo.animation.AnimationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AnimationScreen(
    viewModel: AnimationViewModel,
) {
    val size by animateIntAsState(
        targetValue = (if (viewModel.big) 40 else 10),
        finishedListener = { viewModel.onAnimationDone() }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = viewModel::onSizeChange
        ) {
            Text("Change size")
        }
        Text("Did it ${viewModel.count} times")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Geoff is great!",
                style = MaterialTheme.typography.body1.copy(fontSize = size.sp)
            )
        }
    }
}