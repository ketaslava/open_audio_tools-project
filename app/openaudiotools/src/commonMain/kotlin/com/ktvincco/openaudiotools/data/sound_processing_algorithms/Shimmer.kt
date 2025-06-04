package com.ktvincco.openaudiotools.data.sound_processing_algorithms

import kotlin.math.abs


fun calculateVoiceShimmer(
    pitchGraphRaw: FloatArray,
    spectrogramInHz: Array<FloatArray>
): Float {

    // Settings
    val searchLength = 32

    // Check sufficient length
    if (pitchGraphRaw.size <= searchLength) { return -1F }
    if (spectrogramInHz.size <= searchLength) { return -1F }

    // Crop pitch graph
    val pli = pitchGraphRaw.size - 1
    val pitchGraph = pitchGraphRaw.copyOfRange(pli - searchLength, pli)
    // Crop spectrogram
    val sli = spectrogramInHz.size - 1
    val spectrogram = spectrogramInHz.copyOfRange(sli - searchLength, sli)

    // Find all values belong to pitch in search range
    var values = floatArrayOf()
    for (i in pitchGraph.indices) {

        // Get pitch, filter no pitch detected zones
        val pitch = pitchGraph[i]
        if (pitch < 1F) { continue }

        // Add value
        values += spectrogram[i][pitch.toInt()]
    }

    // Check data
    if (values.size < 2) return -1F

    // Calculate shimmer
    val avgChange = values.toList().zipWithNext { current, next -> abs(next - current) }.average()
    val avgValue = values.average()
    val shimmer = (avgChange / avgValue).toFloat()

    // Apply limit and return result
    return if (shimmer < 1F) shimmer else 1F
}