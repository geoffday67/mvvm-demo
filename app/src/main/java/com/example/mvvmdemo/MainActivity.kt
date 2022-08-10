package com.example.mvvmdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.transform.CircleCropTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = MainViewModel()

        setContent {
            MainScreen(
                // We use the state values in the view model so that Compose magically re-draws our UI when those values change.
                caption = viewModel.name,
                imageURL = viewModel.imageURL,
                onClick = viewModel::handleSubmit,
            )
        }
    }
}

// We use a separate function with its own parameters so that it has no direct reference to the view model.
// Thus it could be re-used anywhere that passes suitable parameters to it.
// The only reference to the view model is in the MainActivity class itself.

@Composable
private fun MainScreen(
    caption: String,
    imageURL: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Hello $caption")
        Button(
            onClick = onClick,
        ) {
            Text("Submit")
        }
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .clip(CircleShape),
            model = imageURL,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MainScreen("Geoff", "") {}
}