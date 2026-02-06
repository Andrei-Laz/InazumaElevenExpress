// scaffold/AppDrawer.kt
package com.example.inazumaelevenexpress.scaffold

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerItem
import androidx.compose.material3.DrawerItemDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.inazumaelevenexpress.R
import com.example.inazumaelevenexpress.navigation.MainDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    currentDestination: MainDestination,
    onDestinationClick: (MainDestination) -> Unit,
    onLogout: () -> Unit
) {
    ModalDrawerSheet {
        // Header with logo/user info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.size(80.dp),
                colorFilter = ColorFilter.tint(DrawerItemDefaults.iconColor)
            )
            Text(
                text = "Welcome, Player!",
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Divider(modifier = Modifier.padding(horizontal = 8.dp))

        // Navigation items (all destinations, not just bottom nav)
        MainDestination.values().forEach { destination ->
            DrawerItem(
                label = { Text(destination.label) },
                icon = {
                    Icon(
                        painter = painterResource(id = getIconRes(destination.icon)),
                        contentDescription = destination.label
                    )
                },
                selected = currentDestination.route == destination.route,
                onClick = { onDestinationClick(destination) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        Divider(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp))

        // Logout button at bottom
        DrawerItem(
            label = { Text("Logout") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "Logout"
                )
            },
            selected = false,
            onClick = onLogout,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

private fun MainDestination.values() = listOf(
    MainDestination.Home,
    MainDestination.Catalog,
    MainDestination.Teams,
    MainDestination.Pvp,
    MainDestination.Pve
)