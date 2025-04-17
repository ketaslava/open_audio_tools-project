package com.ktvincco.openvoiceanalyzer.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.awt.Toolkit


@Composable
actual fun getScreenSizeInDp(): Pair<Dp, Dp> {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    // Assuming a standard density of 1.0f for Desktop (logical density may vary by use case)
    val density = 1.0f // Update this if you have specific density logic for your desktop app
    val widthDp = (screenSize.width / density).toInt()
    val heightDp = (screenSize.height / density).toInt()
    return Pair(widthDp.dp, heightDp.dp)
}


@Composable
actual fun getScreenSizeInPx(): Pair<Int, Int> {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    return Pair(screenSize.width, screenSize.height)
}
