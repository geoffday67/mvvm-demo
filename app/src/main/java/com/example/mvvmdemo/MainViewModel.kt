package com.example.mvvmdemo

import android.util.Log
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
    val name = MutableLiveData<String>()
    val geoff = MutableLiveData<String>()

    init {
        Firebase.database(" https://mvvm-demo-dfbe6-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("/geoff")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    geoff.value = snapshot.value.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("GD", "Error: $error")
                }
            })
    }

    fun immediate() {
        name.value = "Geoff at once"
    }

    fun delay() {
        viewModelScope.launch {
            name.value = "... wait for it"
            delay(2000)
            name.value = "Geoff"
        }
    }
}