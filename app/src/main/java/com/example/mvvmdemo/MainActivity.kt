package com.example.mvvmdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvvmdemo.animation.AnimationViewModel
import com.example.mvvmdemo.di.DiScreenPart1
import com.example.mvvmdemo.di.DiScreenPart2
import com.example.mvvmdemo.help.HelpScreen
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            var viewModel: BaseViewModel? = remember { null }

            Scaffold(
                topBar = {
                    TopBar {
                        navController.navigate("help/${viewModel?.helpId}")
                    }
                },
            ) { padding ->
                NavHost(
                    modifier = Modifier.padding(padding),
                    navController = navController,
                    startDestination = "home",
                ) {
                    composable("home") {
                        MainScreen(getViewModel<MainViewModel> { parametersOf(navController) }.also {
                            viewModel = it
                        })
                    }
                    composable("animation") {
                        AnimationScreen(getViewModel<AnimationViewModel>().also { viewModel = it })
                    }
                    composable("api") {
                        ApiScreen(getViewModel<ApiViewModel>().also { viewModel = it })
                    }
                    composable(
                        route = "help/{id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.IntType
                        }),
                    ) {
                        val id = it.arguments?.getInt("id") ?: R.string.help_general
                        HelpScreen(id)
                    }
                    composable("di") {
                        Column() {
                            DiScreenPart1()
                            DiScreenPart2()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    onHelpClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        actions = {
            IconButton(
                onClick = onHelpClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = null,
                )
            }
        }
    )
}

// We use a separate function with its own parameters so that it has no direct reference to the view model.
// Thus it could be re-used anywhere that passes suitable parameters to it.
// The only reference to the view model is in the MainActivity class itself.

