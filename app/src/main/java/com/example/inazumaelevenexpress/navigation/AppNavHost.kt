// navigation/AppNavHost.kt
package com.example.inazumaelevenexpress.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inazumaelevenexpress.screens.auth.AuthNavGraph
import com.example.inazumaelevenexpress.screens.main.MainNavGraph
import com.example.inazumaelevenexpress.viewmodels.UserStateViewModel

@Composable
fun AppNavHost(
    userStateViewModel: UserStateViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val isAuthenticated by userStateViewModel.isAuthenticated.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) AppDestination.Main.route else AppDestination.Auth.route
    ) {
        // Auth graph - NO scaffold wrapper
        composable(route = AppDestination.Auth.route) {
            AuthNavGraph(
                navController = navController,
                onLoginSuccess = {
                    userStateViewModel.setAuthenticated(true)
                    navController.navigate(AppDestination.Main.route) {
                        popUpTo(AppDestination.Auth.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Main app graph - WITH scaffold wrapper
        composable(route = AppDestination.Main.route) {
            MainNavGraph(
                navController = navController,
                onLogout = {
                    userStateViewModel.setAuthenticated(false)
                    navController.navigate(AppDestination.Auth.route) {
                        popUpTo(AppDestination.Main.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}