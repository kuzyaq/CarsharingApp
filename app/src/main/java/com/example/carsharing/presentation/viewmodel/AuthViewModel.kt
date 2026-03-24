package com.example.carsharing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.carsharing.domain.model.UserRole
import com.example.carsharing.domain.usecase.auth.LoginUseCase
import com.example.carsharing.domain.usecase.auth.RegisterClientUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerClientUseCase: RegisterClientUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String, role: UserRole, onSuccess: (UserRole) -> Unit) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            val result = loginUseCase(email, password, role)
            result.onSuccess { user ->
                _uiState.value = AuthUiState()
                onSuccess(user.role)
            }.onFailure {
                _uiState.value = AuthUiState(error = it.message ?: "Ошибка входа")
            }
        }
    }

    fun register(fullName: String, email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            val result = registerClientUseCase(fullName, email, password)
            result.onSuccess {
                _uiState.value = AuthUiState()
                onSuccess()
            }.onFailure {
                _uiState.value = AuthUiState(error = it.message ?: "Ошибка регистрации")
            }
        }
    }

    companion object {
        fun factory(
            loginUseCase: LoginUseCase,
            registerClientUseCase: RegisterClientUseCase
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(loginUseCase, registerClientUseCase) as T
            }
        }
    }
}
