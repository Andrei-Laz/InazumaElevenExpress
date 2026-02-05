// navigation/AppNavigation.kt
package com.example.inazumaelevenexpress.navigation

import kotlinx.serialization.Serializable

// Top-level destinations
sealed class AppDestination(val route: String) {
    object Auth : AppDestination("auth")
    object Main : AppDestination("main")
}

// Auth graph destinations
sealed class AuthDestination(val route: String) {
    object Initial : AuthDestination("initial")
    object Login : AuthDestination("login")
    object Register : AuthDestination("register")

    companion object {
        fun fromRoute(route: String): AuthDestination =
            when (route) {
                Initial.route -> Initial
                Login.route -> Login
                Register.route -> Register
                else -> Initial
            }
    }
}

// Main graph destinations (bottom nav items)
sealed class MainDestination(val route: String, val icon: String, val label: String) {
    object Home : MainDestination("home", "home", "Home")
    object Catalog : MainDestination("catalog", "person", "Players")
    object Teams : MainDestination("teams", "groups", "Teams")
    object Pvp : MainDestination("pvp", "sports_soccer", "PvP")
    object Pve : MainDestination("pve", "gamepad", "PvE")

    companion object {
        val bottomNavItems = listOf(Home, Catalog, Teams) // Only show these in bottom nav
        fun fromRoute(route: String): MainDestination =
            when (route) {
                Home.route -> Home
                Catalog.route -> Catalog
                Teams.route -> Teams
                Pvp.route -> Pvp
                Pve.route -> Pve
                else -> Home
            }
    }
}

// Navigation arguments (type-safe)
@Serializable
data class TeamDetailsArgs(val teamId: Int)

@Serializable
data class PlayerDetailsArgs(val playerId: Int)