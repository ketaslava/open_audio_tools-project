package com.ktvincco.openaudiotools.data.sound_processing_algorithms

import com.ktvincco.openaudiotools.Settings


const val maxVoiceFreqHz = 4096


fun getVoiceSpectrumInHz(currentSample: FloatArray): FloatArray {

    // Get raw fft
    val magnitudes = fastFourierTransform(currentSample)

    // Transform FFT to HzFFT
    val frequencyResolution = (Settings.getSampleRate() / magnitudes.size.toDouble()) / 2

    var fftHz: FloatArray = floatArrayOf()
    for (hz in 0..maxVoiceFreqHz) {
        fftHz += magnitudes[(hz / frequencyResolution).toInt()]
    }

    return fftHz
}