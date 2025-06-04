package com.ktvincco.openaudiotools.data.sound_processing_algorithms


fun calculateLoudness(currentSample: FloatArray): Float {
    return currentSample.maxOrNull() ?: 0F
}