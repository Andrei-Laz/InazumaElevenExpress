// screens/auth/InitialScreen.kt
package com.example.inazumaelevenexpress.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.inazumaelevenexpress.R

@Composable
fun InitialScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App logo
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // App title
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold
        )

        // Login button
        Button(
            onClick = onLoginClick,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 48.dp)
        ) {
            Text("Login")
        }

        // Register button
        Button(
            onClick = onRegisterClick,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 48.dp),
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Text("Register")
        }
    }
}