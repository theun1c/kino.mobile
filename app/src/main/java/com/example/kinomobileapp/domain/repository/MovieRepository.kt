package com.example.kinomobileapp.domain.repository

import com.example.kinomobileapp.data.api.PocketBaseApi
import com.example.kinomobileapp.domain.models.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: PocketBaseApi
){
    suspend fun getMovies(): List<Movie> {
        return try {
            val response = api.getMovies()
            response.items
        } catch (e: Exception){
            emptyList()
        }
    }
}