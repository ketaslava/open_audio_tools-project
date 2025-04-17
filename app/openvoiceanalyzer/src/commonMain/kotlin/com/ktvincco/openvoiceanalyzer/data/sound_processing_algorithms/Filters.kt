package com.ktvincco.openvoiceanalyzer.data.sound_processing_algorithms

import kotlin.math.PI
import kotlin.math.abs


fun applyHanningWindow(samples: FloatArray): FloatArray {
    val windowedSamples = FloatArray(samples.size)
    for (i in samples.indices) {
        val hann = 0.5f * (1 - kotlin.math.cos(2.0 * PI * i / (samples.size - 1))).toFloat()
        windowedSamples[i] = samples[i] * hann
    }
    return windowedSamples
}


fun applyWindowSmooth(spectrogram: FloatArray, windowSize: Int, step: Int): FloatArray {
    val smoothSpectrogram = FloatArray(spectrogram.size)
    for (hz in smoothSpectrogram.indices) {
        var value = 0F
        for (i in -windowSize / 2..windowSize / 2 step step) {
            val distance = 1F - (abs(i).toFloat() / (windowSize / 2).toFloat())
            val index = hz + i
            if (index in spectrogram.indices) {
                value += spectrogram[index] * distance
            }
        }
        smoothSpectrogram[hz] = value / (windowSize / step)
    }
    return smoothSpectrogram
}