package com.ktvincco.openaudiotools.data.sound_processing_algorithms

import kotlin.math.abs


fun calculateVoiceJitter(pitchGraphRaw: FloatArray): Float {

    // Configuration
    val searchLength = 32

    // Crop data
    val pitchGraph = if(pitchGraphRaw.size > searchLength) pitchGraphRaw.copyOfRange(
        pitchGraphRaw.size - searchLength, pitchGraphRaw.size) else pitchGraphRaw

    // Cleanup data
    val filteredPitch = pitchGraph.filter { it > 1F }
    if (filteredPitch.size < 2) return -1F

    // Calculate
    val deltas = filteredPitch.zipWithNext { current, next -> abs(next - current) / current }

    // Normalize
    val jitter = deltas.average().toFloat()

    // Apply limits
    return if (jitter in 0F..1F) jitter else -1F
}