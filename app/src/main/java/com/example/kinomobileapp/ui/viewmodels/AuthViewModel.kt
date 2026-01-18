package com.example.kinomobileapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinomobileapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess.asStateFlow()

    fun login(email: String, password: String){
        if(email.isEmpty() || password.isEmpty()){
            _error.value = "Заполоните все поля"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _loginSuccess.value = false

            try{
                val result = authRepository.login(email, password)
                if(result.isSuccess){
                    _loginSuccess.value = true
                } else {
                    _error.value = "Ошибка входа"
                }
            } catch (e: Exception){
                _error.value = "Ошибка ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetState(){
        _error.value = null
        _loginSuccess.value = false
    }
}