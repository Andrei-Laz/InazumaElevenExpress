// viewmodels/UserStateViewModel.kt
package com.example.inazumaelevenexpress.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserStateViewModel @Inject constructor() : ViewModel() {
    private val _isAuthenticated = mutableStateOf(false)
    val isAuthenticated = _isAuthenticated

    fun setAuthenticated(value: Boolean) {
        _isAuthenticated.value = value
    }
}