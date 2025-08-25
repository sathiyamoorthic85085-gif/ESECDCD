package com.example.dressmonitor.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light Theme
val LightColors = lightColorScheme(
    primary = Color(0xFF3F51B5),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFC5CAE9),
    onPrimaryContainer = Color(0xFF1A237E),

    secondary = Color(0xFFFF9800),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFFFE0B2),
    onSecondaryContainer = Color(0xFFE65100),

    background = Color(0xFFF5F5F5),
    onBackground = Color(0xFF212121),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF212121),

    tertiary = Color(0xFF4CAF50),
    onTertiary = Color.White,
    error = Color(0xFFD32F2F),
    onError = Color.White
)

// Dark Theme
val DarkColors = darkColorScheme(
    primary = Color(0xFF7986CB),
    onPrimary = Color(0xFF0D0D0D),
    primaryContainer = Color(0xFF303F9F),
    onPrimaryContainer = Color(0xFFC5CAE9),

    secondary = Color(0xFFFFB74D),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFC88719),
    onSecondaryContainer = Color(0xFFFFE0B2),

    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),

    tertiary = Color(0xFF81C784),
    onTertiary = Color.Black,
    error = Color(0xFFE57373),
    onError = Color.Black
)
