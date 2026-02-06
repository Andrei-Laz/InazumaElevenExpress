// screens/teams/TeamsViewModel.kt
package com.example.inazumaelevenexpress.screens.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TeamsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val teams: List<Team> = emptyList()
)

@HiltViewModel
class TeamsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(TeamsUiState())
    val uiState: StateFlow<TeamsUiState> = _uiState

    init {
        loadTeams()
    }

    private fun loadTeams() {
        viewModelScope.launch {
            _uiState.value = TeamsUiState(isLoading = true)
            try {
                // TODO: Call API service to fetch teams
                val teams = listOf<Team>() // Replace with actual API call
                _uiState.value = TeamsUiState(teams = teams)
            } catch (e: Exception) {
                _uiState.value = TeamsUiState(error = e.message)
            }
        }
    }
}

// Mock data class - replace with actual API models
data class Team(
    val id: Int,
    val name: String
)