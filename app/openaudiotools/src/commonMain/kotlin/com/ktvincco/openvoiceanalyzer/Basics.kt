package com.ktvincco.openaudiotools

import androidx.compose.ui.graphics.Color
import kotlin.math.log10


fun map(source: Float, minSource: Float, maxSource: Float,
                minTarget: Float, maxTarget: Float): Float {
    val s = (source - minSource) / (maxSource - minSource)
    return minTarget + (maxTarget - minTarget) * s
}


fun amplitudeToDecibels(amplitude: Float, epsilon: Float = 1e-6f): Float {
    return 20 * log10(amplitude + epsilon)
}


fun normalizeDecibels(decibel: Float, minDb: Float = -120f, maxDb: Float = 0f): Float {
    return (decibel - minDb) / (maxDb - minDb)
}


fun getPlasmaColor(value: Float): Color {
    val plasmaColors = arrayOf(
        floatArrayOf(0.2f, 0.0f, 0.4f),  // Purple
        floatArrayOf(0.8f, 0.2f, 0.9f),  // Pink
        floatArrayOf(1.0f, 0.9f, 0.0f),  // Yellow
        floatArrayOf(1.0f, 1.0f, 1.0f)   // White
    )
    val i = (value * (plasmaColors.size - 1)).toInt()
    val t = value * (plasmaColors.size - 1) - i
    val c1 = plasmaColors[i]
    val c2 = plasmaColors[minOf(i + 1, plasmaColors.size - 1)]

    val r = c1[0] + t * (c2[0] - c1[0])
    val g = c1[1] + t * (c2[1] - c1[1])
    val b = c1[2] + t * (c2[2] - c1[2])

    return Color(r, g, b)
}