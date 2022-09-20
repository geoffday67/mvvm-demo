package com.example.mvvmdemo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiViewModel : ViewModel() {
    interface PoliceApi {
        @GET("api/forces")
        suspend fun getForceList(): List<ForceResponse>
    }

    private val policeService: PoliceApi = Retrofit.Builder()
        .baseUrl("https://data.police.uk")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PoliceApi::class.java)

    var forces = mutableStateListOf<Force>()

    init {
        viewModelScope.launch {
            forces.clear()
            forces.addAll(
                policeService.getForceList()
                    .map { it.toModel() }
            )
        }
    }

    // Class representing the API response.
    // The variable names here should match the returned fields from the API.
    // You don't have to declare all the fields if there are some you're not interested in.
    // Make the types nullable as if they're missing from the response they'll stay as null.
    data class ForceResponse(
        val id: String? = null,
        val name: String? = null,
    )

    // Class representing the internal model of a force.
    // Keep this separate from the API model so that if (when!) the API changes you only have to change the API model and how it's converted.
    // In general these variable should be non-nullable and given explicit values during conversion from the API response.
    data class Force(
        val id: String,
        val name: String,
    )

    // Kotlin extension method to convert to internal model, dealing with null values.
    private fun ForceResponse.toModel() =
        Force(
            id = id ?: "",
            name = name ?: "",
        )
}