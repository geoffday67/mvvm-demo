package com.example.mvvmdemo

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var name: String by mutableStateOf("")

    /*var geoff: String by mutableStateOf("")

    init {
        Firebase.database(" https://mvvm-demo-dfbe6-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("/geoff")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    geoff = snapshot.value.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("GD", "Error: $error")
                }
            })
    }*/

    fun handleSubmit() {
        delay()
    }

    fun immediate() {
        name = "Geoff at once"
    }

    fun delay() {
        viewModelScope.launch {
            name = "... wait for it"
            delay(2000)
            name = "Geoff"
        }
    }
}