package com.example.kinomobileapp.domain.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("ratingKinoPoisk") val ratingKinoPoisk: Double,
    @SerializedName("ratingIMDB") val ratingIMDB: Double,
    @SerializedName("genre") val genre: String,
    @SerializedName("country") val country: String,
    @SerializedName("director") val director: String
)