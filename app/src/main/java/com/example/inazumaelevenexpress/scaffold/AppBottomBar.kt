// scaffold/AppBottomBar.kt
package com.example.inazumaelevenexpress.scaffold

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.inazumaelevenexpress.navigation.MainDestination

@Composable
fun AppBottomBar(
    currentDestination: MainDestination,
    destinations: List<MainDestination>,
    onDestinationClick: (MainDestination) -> Unit
) {
    NavigationBar {
        destinations.forEach { destination ->
            NavigationBarItem(
                selected = currentDestination.route == destination.route,
                onClick = { onDestinationClick(destination) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = getIconRes(destination.icon)),
                        contentDescription = destination.label
                    )
                },
                label = { Text(destination.label) }
            )
        }
    }
}

// Helper to map icon names to resources (simplified example)
private fun getIconRes(iconName: String): Int = when (iconName) {
    "home" -> R.drawable.ic_home
    "person" -> R.drawable.ic_person
    "groups" -> R.drawable.ic_groups
    "sports_soccer" -> R.drawable.ic_soccer
    "gamepad" -> R.drawable.ic_gamepad
    else -> R.drawable.ic_home
}