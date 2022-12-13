package com.example.mvvmdemo

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
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
import coil.compose.AsyncImage
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = getViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Button(
            onClick = viewModel::handleSubmit,
        ) {
            Text("Submit")
        }
        Login(
            onLogin = viewModel::handleLogin,
            loggedIn = viewModel.loggedIn,
        )
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .clip(CircleShape),
            model = viewModel.imageURL,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Button(
            onClick = viewModel::handleAnimation,
        ) {
            Text("Animation screen")
        }
        Button(
            onClick = viewModel::handleApi,
        ) {
            Text("API screen")
        }
        Button(
            onClick = viewModel::handleDi,
        ) {
            Text("DI screen")
        }
        GetImage(
            uri = viewModel.imageSource,
            onPick = viewModel::handleImage,
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
