// screens/catalog/CatalogViewModel.kt
package com.example.inazumaelevenexpress.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CatalogUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val characters: List<Character> = emptyList()
)

@HiltViewModel
class CatalogViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CatalogUiState())
    val uiState: StateFlow<CatalogUiState> = _uiState

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _uiState.value = CatalogUiState(isLoading = true)
            try {
                // TODO: Call API service to fetch characters
                val characters = listOf<Character>() // Replace with actual API call
                _uiState.value = CatalogUiState(characters = characters)
            } catch (e: Exception) {
                _uiState.value = CatalogUiState(error = e.message)
            }
        }
    }
}

// Mock data class - replace with actual API models
data class Character(
    val id: Int,
    val name: String,
    val element: String
)