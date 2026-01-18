package com.example.kinomobileapp.domain.repository

import com.example.kinomobileapp.data.api.PocketBaseApi
import com.example.kinomobileapp.domain.models.LoginRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: PocketBaseApi
) {
    suspend fun login(email: String, password: String): Result<String> {
        return try {
            val request = LoginRequest(email = email, password = password)
            val response = api.login(request)

            if(response.isSuccessful){
                val token = response.body()?.token ?: ""
                Result.success("Успешный вход, токен ${token}")
            } else {
                Result.failure(Exception("Ошибка входа"))
            }
        } catch (e: Exception){
            Result.failure(Exception(e.message))
        }
    }
}