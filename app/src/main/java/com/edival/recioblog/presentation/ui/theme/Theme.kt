package com.edival.recioblog.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = primaryLightColor,
    primaryVariant = primaryDarkColor,
    secondary = secondaryColor,
    background = secondaryTextColor,
    surface = secondaryTextColor,
    onPrimary = secondaryTextColor,
    onSecondary = primaryTextColor,
    onBackground = primaryTextColor,
    onSurface = primaryTextColor
)
private val LightColorPalette = lightColors(
    primary = primaryColor,
    primaryVariant = primaryDarkColor,
    secondary = secondaryColor,
    background = primaryTextColor,
    surface = primaryTextColor,
    onPrimary = primaryTextColor,
    onSecondary = secondaryTextColor,
    onBackground = secondaryTextColor,
    onSurface = secondaryTextColor
)

@Composable
fun RecioBlogTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}