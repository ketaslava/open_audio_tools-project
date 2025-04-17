package com.ktvincco.openvoiceanalyzer

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable


private val ThemeColors = darkColors(
    primary = ColorPalette.getBackgroundColor(),
    primaryVariant = ColorPalette.getBackgroundColor(),
    secondary = ColorPalette.getBlockColor()
)


@Composable
fun MainApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ThemeColors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}
