package com.example.carsharing.presentation.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carsharing.data.local.prefs.AppPreferences
import com.example.carsharing.ui.theme.ThemeMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Экран настроек приложения.
 * Сейчас реализован только выбор темы (светлая/тёмная/системная).
 */
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val appPreferences = remember { AppPreferences(context) }

    val vm: SettingsViewModel = viewModel(
        factory = SettingsViewModel.factory(appPreferences as AppPreferences)
    )
    val themeMode by vm.themeMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Настройки",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Тема приложения",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        ThemeOption(
            title = "Системная",
            selected = themeMode == ThemeMode.SYSTEM,
            onClick = { vm.onThemeChanged(ThemeMode.SYSTEM) }
        )
        ThemeOption(
            title = "Светлая",
            selected = themeMode == ThemeMode.LIGHT,
            onClick = { vm.onThemeChanged(ThemeMode.LIGHT) }
        )
        ThemeOption(
            title = "Тёмная",
            selected = themeMode == ThemeMode.DARK,
            onClick = { vm.onThemeChanged(ThemeMode.DARK) }
        )
    }
}

@Composable
private fun ThemeOption(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Spacer(modifier = Modifier.height(0.dp).weight(0.01f))
        Text(text = title, fontSize = 16.sp)
    }
}

class SettingsViewModel(
    private val appPreferences: AppPreferences
) : androidx.lifecycle.ViewModel() {

    val themeMode = appPreferences.themeMode.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ThemeMode.SYSTEM
    )

    fun onThemeChanged(mode: ThemeMode) {
        viewModelScope.launch {
            appPreferences.setThemeMode(mode)
        }
    }

    companion object {
        fun factory(appPreferences: AppPreferences): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                        return SettingsViewModel(appPreferences) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
        }
    }
}

