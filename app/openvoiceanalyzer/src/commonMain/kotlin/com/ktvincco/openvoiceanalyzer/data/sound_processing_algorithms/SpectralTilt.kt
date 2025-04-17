package com.ktvincco.openvoiceanalyzer.data.sound_processing_algorithms

import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.ln


fun calculateSpectralTilt(spectrumInHz: FloatArray): Float {

    // Normalize input
    val maxMagnitude = spectrumInHz.maxOrNull() ?: 1F
    val spectrogram = spectrumInHz.map { it / maxMagnitude }.toFloatArray()

    // Check
    if (spectrogram.isEmpty()) return 0F

    // Calculate log
    val logSpectrogram = spectrogram.map { ln(it + 1e-10F) }.toFloatArray()

    // Calculate tilt
    val n = logSpectrogram.size
    var sumX = 0.0
    var sumY = 0.0
    var sumXY = 0.0
    var sumX2 = 0.0

    for (i in logSpectrogram.indices) {
        val x = i.toDouble()
        val y = logSpectrogram[i].toDouble()

        sumX += x
        sumY += y
        sumXY += x * y
        sumX2 += x * x
    }

    val slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX)

    // Normalize output
    val normalizedTilt = (slope.toFloat() * spectrogram.size.toFloat()) / PI.toFloat()

    // Apply sigmoid
    val sigmoidTilt = (2F / (1F + exp(-normalizedTilt))) - 1F

    return sigmoidTilt
}

