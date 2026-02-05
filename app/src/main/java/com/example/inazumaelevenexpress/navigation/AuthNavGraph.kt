// navigation/AuthNavGraph.kt
package com.example.inazumaelevenexpress.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inazumaelevenexpress.screens.auth.InitialScreen
import com.example.inazumaelevenexpress.screens.auth.LoginScreen
import com.example.inazumaelevenexpress.screens.auth.RegisterScreen

@Composable
fun AuthNavGraph(
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    // Minimal scaffold - no top/bottom bars
    Scaffold { paddingValues ->
        Box(modifier = androidx.compose.ui.Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = AuthDestination.Initial.route
            ) {
                composable(route = AuthDestination.Initial.route) {
                    InitialScreen(
                        onLoginClick = { navController.navigate(AuthDestination.Login.route) },
                        onRegisterClick = { navController.navigate(AuthDestination.Register.route) }
                    )
                }

                composable(route = AuthDestination.Login.route) {
                    LoginScreen(
                        onLoginSuccess = onLoginSuccess,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }

                composable(route = AuthDestination.Register.route) {
                    RegisterScreen(
                        onRegisterSuccess = onLoginSuccess,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}