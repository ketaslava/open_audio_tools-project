package com.ktvincco.openaudiorecorder.data.sound_processing_algorithms

import com.ktvincco.openaudiorecorder.Settings


fun calculateVoiceProsody(pitchGraph: FloatArray): Float {

    // Settings
    val processingWindowDurationSec = 8
    val minDataSize = 8

    // Check input
    if (pitchGraph.isEmpty()) { return -1F }

    // Calculate window size
    var processingWindowSize = ((1F / Settings.getProcessingSampleDurationSec()) *
            processingWindowDurationSec).toInt() - 1
    if (pitchGraph.size <= processingWindowSize) {
        processingWindowSize = pitchGraph.size - 1
    }

    // Corp data, filter -1.0 and check size
    val data = pitchGraph.copyOfRange(
        (pitchGraph.size - 1) - processingWindowSize, pitchGraph.size - 1).filter { it > 1F }
    if (data.size < minDataSize) { return -1F }

    // Get average
    val avg = data.average().toFloat()

    // Split data in to above and below, get averages
    val avgAbove = data.filter { it > avg }.average().toFloat()
    val avgBelow = data.filter { it < avg }.average().toFloat()

    // Check
    if (avgAbove == 0F || avgBelow == 0F) { return -1F }

    // Calculate absolute avg change
    val avgChange = avgAbove - avgBelow

    // Calculate change as fraction of average
    return (avgChange / avg).coerceIn(0F, 1F)
}