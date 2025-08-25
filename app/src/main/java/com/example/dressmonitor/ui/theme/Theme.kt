package com.example.dressmonitor.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Default to system theme
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColors // Use DarkColors from Color.kt
    } else {
        LightColors // Use LightColors from Color.kt
    }

    MaterialTheme(
        colorScheme = colorScheme, // Apply the selected color scheme
        typography = Typography, // Assuming you have Typography defined
        // shapes = Shapes,         // Assuming you have Shapes defined - Commenting out if not present
        content = content
    )
}
