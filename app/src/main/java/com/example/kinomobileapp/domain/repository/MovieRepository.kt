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
            println("✅ Получено фильмов: ${response.items.size}")
            response.items.forEach { movie ->
                println("🎬 ${movie.title} - ${movie.ratingKinoPoisk}")
            }
            response.items

        } catch (e: Exception){
            println("❌ Ошибка загрузки фильмов: ${e.message}")
            e.printStackTrace()  // добавим стектрейс
            emptyList()
        }
    }
}