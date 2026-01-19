package com.example.kinomobileapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinomobileapp.domain.models.Movie
import com.example.kinomobileapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateMovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _updateSuccess = MutableStateFlow(false)
    val updateSuccess: StateFlow<Boolean> = _updateSuccess.asStateFlow()

    // Загружаем фильм по ID
    fun loadMovie(movieId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val result = repository.getMovieById(movieId)
                if (result.isSuccess) {
                    _movie.value = result.getOrNull()
                } else {
                    _error.value = "Не удалось загрузить фильм"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Обновляем фильм
    fun updateMovie(
        movieId: String,
        title: String,
        description: String,
        ratingKinoPoisk: Double,
        ratingIMDB: Double,
        genre: String,
        country: String,
        director: String
    ) {
        if (title.isEmpty() || description.isEmpty()) {
            _error.value = "Заполните обязательные поля"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _updateSuccess.value = false

            try {
                val movie = Movie(
                    id = movieId,
                    title = title,
                    description = description,
                    ratingKinoPoisk = ratingKinoPoisk,
                    ratingIMDB = ratingIMDB,
                    genre = genre,
                    country = country,
                    director = director
                )

                val result = repository.updateMovie(movie)

                if (result.isSuccess) {
                    _updateSuccess.value = true
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Ошибка обновления"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetState() {
        _error.value = null
        _updateSuccess.value = false
    }
}