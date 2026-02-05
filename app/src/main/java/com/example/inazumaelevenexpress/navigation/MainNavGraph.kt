// navigation/MainNavGraph.kt
package com.example.inazumaelevenexpress.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inazumaelevenexpress.scaffold.MainScaffold
import com.example.inazumaelevenexpress.screens.home.MainMenuScreen
import com.example.inazumaelevenexpress.screens.catalog.CatalogScreen
import com.example.inazumaelevenexpress.screens.teams.TeamsScreen
import com.example.inazumaelevenexpress.screens.pvp.PVPScreen
import com.example.inazumaelevenexpress.screens.pve.PVEScreen

@Composable
fun MainNavGraph(
    navController: NavController,
    onLogout: () -> Unit
) {
    val innerNavController = rememberNavController()
    var currentDestination by remember { mutableIntStateOf(0) }

    MainScaffold(
        currentDestination = MainDestination.bottomNavItems[currentDestination],
        onDestinationClick = { destination ->
            currentDestination = MainDestination.bottomNavItems.indexOf(destination)
            innerNavController.navigate(destination.route) {
                popUpTo(innerNavController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        },
        onLogout = onLogout
    ) {
        // Inner NavHost for bottom navigation
        NavHost(
            navController = innerNavController,
            startDestination = MainDestination.Home.route
        ) {
            composable(route = MainDestination.Home.route) {
                MainMenuScreen()
            }

            composable(route = MainDestination.Catalog.route) {
                CatalogScreen()
            }

            composable(route = MainDestination.Teams.route) {
                TeamsScreen()
            }

            composable(route = MainDestination.Pvp.route) {
                PvpScreen()
            }

            composable(route = MainDestination.Pve.route) {
                PveScreen()
            }
        }
    }
}