// scaffold/MainScaffold.kt
package com.example.inazumaelevenexpress.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.inazumaelevenexpress.navigation.MainDestination
import kotlinx.coroutines.launch

@Composable
fun MainScaffold(
    currentDestination: MainDestination,
    onDestinationClick: (MainDestination) -> Unit,
    onLogout: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(
                currentDestination = currentDestination,
                onDestinationClick = { destination ->
                    onDestinationClick(destination)
                    scope.launch { drawerState.close() }
                },
                onLogout = {
                    onLogout()
                    scope.launch { drawerState.close() }
                }
            )
        },
        drawerState = drawerState
    ) {
        androidx.compose.material3.Scaffold(
            topBar = {
                AppTopBar(
                    title = currentDestination.label,
                    onNavigationIconClick = { scope.launch { drawerState.open() } }
                )
            },
            bottomBar = {
                AppBottomBar(
                    currentDestination = currentDestination,
                    destinations = MainDestination.bottomNavItems,
                    onDestinationClick = onDestinationClick
                )
            }
        ) { paddingValues ->
            content(paddingValues)
        }
    }
}