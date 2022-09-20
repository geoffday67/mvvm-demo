package com.example.mvvmdemo

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainViewModel(
    private val navController: NavHostController,
    ) : ViewModel() {
    var name: String by mutableStateOf("")
    var imageURL: String by mutableStateOf("https://www.gstatic.com/webp/gallery/4.jpg")
    var loggedIn: Boolean? by mutableStateOf(null)
    var imageSource: Uri? by mutableStateOf(null)

    fun handleSubmit() {
        delay()
    }

    fun handleLogin() {
        viewModelScope.launch {
            loggedIn = try {
                Firebase.auth
                    .signInWithEmailAndPassword("geoff@isgreat.com", "the_truth")
                    .await()
                true
            } catch (ignore: Exception) {
                false
            }
        }
    }

    fun handleNext() {
        navController.navigate("great")
    }

    fun handleApi() {
        navController.navigate("api")
    }

    fun handleImage(uri: Uri) {
        imageSource = uri
    }

    fun immediate() {
        name = "Geoff at once"
    }

    fun delay() {
        viewModelScope.launch {
            name = "... wait for it"
            delay(2000)

            // Here we update the state of the app's data. The UI will change to reflect changes in the mutableState variables.
            name = "Fire!!"
            imageURL = "https://www.gstatic.com/webp/gallery/5.jpg"
        }
    }
}