package com.example.mvvmdemo

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val viewModel = MainViewModel(navController)

            NavHost(
                navController = navController,
                startDestination = "home",
            ) {
                composable("home") {
                    MainScreen(
                        // We use the state values in the view model so that Compose magically re-draws our UI when those values change.
                        caption = viewModel.name,
                        imageURL = viewModel.imageURL,
                        onClick = viewModel::handleSubmit,
                        onLogin = viewModel::handleLogin,
                        loggedIn = viewModel.loggedIn,
                        onNext = viewModel::handleNext,
                        imageSource = viewModel.imageSource,
                        onImagePick = viewModel::handleImage,
                    )
                }
                composable("great") {
                    Text("Geoff is great!")
                }
            }
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
    onLogin: () -> Unit,
    loggedIn: Boolean?,
    onNext: () -> Unit,
    imageSource: Uri?,
    onImagePick: (Uri) -> Unit,
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
        Login(
            onLogin = onLogin,
            loggedIn = loggedIn,
        )
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .clip(CircleShape),
            model = imageURL,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Button(
            onClick = onNext,
        ) {
            Text("Next screen")
        }
        GetImage(
            uri = imageSource,
            onPick = onImagePick,
        )
    }
}

@Composable
fun GetImage(
    uri: Uri?,
    onPick: (Uri) -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        if (it != null) {
            onPick(it)
        }
    }

    Button(
        onClick = {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
    ) {
        Text("Pick image")
    }

    AsyncImage(
        model = uri,
        contentDescription = null,
    )
}

@Composable
fun Login(
    loggedIn: Boolean?,
    onLogin: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            modifier = Modifier.padding(bottom = 8.dp),
            onClick = onLogin,
        ) {
            Text("Login")
        }
        if (loggedIn != null) {
            Text(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = if (loggedIn) Color.Green else Color.Red,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(all = 8.dp),
                text = if (loggedIn) "Welcome!" else "I don't know you!"
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Login(
        loggedIn = false,
        onLogin = {},
    )
}