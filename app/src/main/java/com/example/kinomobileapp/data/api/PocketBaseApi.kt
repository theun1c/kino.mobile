package com.example.kinomobileapp.data.api

import com.example.kinomobileapp.domain.models.MovieResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PocketBaseApi {
    @GET("api/collections/movies/records")
    suspend fun getMovies(
        @Query("page") page: Int = 1,
        @Query("perPage") perPage: Int = 50
    ) : MovieResponse

    @DELETE("/api/collections/movies/records/{id}")
    suspend fun deleteMovie(@Path("id") id: String): Response<Unit>
}