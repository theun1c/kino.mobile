package com.example.kinomobileapp.domain.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("identity") val email: String,
    @SerializedName("password") val password: String
)

data class LoginResponse(
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: User
)

data class User(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String?,
    @SerializedName("created") val created: String,
    @SerializedName("updated") val updated: String
)