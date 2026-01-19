package com.example.kinomobileapp.domain.repository

import androidx.compose.ui.graphics.RectangleShape
import com.example.kinomobileapp.data.api.PocketBaseApi
import com.example.kinomobileapp.domain.models.CreateMovieRequest
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

    suspend fun deleteMovie(movieId: String): Boolean {
        return try {
            val response = api.deleteMovie(movieId)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun createMovie(movie: Movie): Result<Movie> {
        return try {
            val response = api.createMovie(movie)
            if (response.isSuccessful) {
                val createdMovie = response.body()
                if (createdMovie != null) {
                    Result.success(createdMovie)
                } else {
                    Result.failure(Exception("Пустой ответ от сервера"))
                }
            } else {
                Result.failure(Exception("Ошибка: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateMovie(movie: Movie): Result<Movie> {
        return try {
            val response = api.updateMovie(movie.id, movie)
            if (response.isSuccessful) {
                val updatedMovie = response.body()
                if (updatedMovie != null) {
                    Result.success(updatedMovie)
                } else {
                    Result.failure(Exception("Пустой ответ от сервера"))
                }
            } else {
                Result.failure(Exception("Ошибка обновления: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMovieById(id: String): Result<Movie> {
        return try {
            val movie = api.getMovieById(id)
            Result.success(movie)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}