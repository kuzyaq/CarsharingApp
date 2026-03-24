package com.example.carsharing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.carsharing.domain.model.Car
import com.example.carsharing.domain.usecase.car.ObserveCarsUseCase
import com.example.carsharing.domain.usecase.car.RefreshCarsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observeCarsUseCase: ObserveCarsUseCase,
    private val refreshCarsUseCase: RefreshCarsUseCase
) : ViewModel() {
    private val query = MutableStateFlow("")
    val cars: StateFlow<List<Car>> = query
        .flatMapLatest { observeCarsUseCase(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onQueryChanged(value: String) {
        query.value = value
    }

    fun refresh() {
        viewModelScope.launch { refreshCarsUseCase() }
    }

    companion object {
        fun factory(
            observeCarsUseCase: ObserveCarsUseCase,
            refreshCarsUseCase: RefreshCarsUseCase
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(observeCarsUseCase, refreshCarsUseCase) as T
            }
        }
    }
}
