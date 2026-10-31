package com.supersuman.andha.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryLightColor,
    tertiary = PrimaryDarkColor
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryDarkColor,
    tertiary = PrimaryLightColor
)

@Composable
fun AndhaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
