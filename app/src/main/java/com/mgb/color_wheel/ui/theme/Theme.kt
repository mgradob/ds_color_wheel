package com.mgb.color_wheel.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = SDTeal,
    primaryVariant = SDGreen,
    secondary = SDOrange,
    background = SDBackground,
    surface = SDElementBackground,
)

@Composable
fun Color_wheelTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}