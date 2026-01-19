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
class AddMovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _addSuccess = MutableStateFlow(false)
    val addSuccess: StateFlow<Boolean> = _addSuccess.asStateFlow()

    fun addMovie(
        title: String,
        description: String,
        ratingKinoPoisk: Double,
        ratingIMDB: Double,
        genre: String,
        country: String,
        director: String
    ) {
        if (title.isEmpty() || description.isEmpty()) {
            _error.value = "Заполните название и описание"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _addSuccess.value = false

            try {
                val movie = Movie(
                    id = "",
                    title = title,
                    description = description,
                    ratingKinoPoisk = ratingKinoPoisk,
                    ratingIMDB = ratingIMDB,
                    genre = genre,
                    country = country,
                    director = director
                )

                val result = repository.createMovie(movie)

                if (result.isSuccess) {
                    _addSuccess.value = true
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Ошибка добавления"
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
        _addSuccess.value = false
    }
}