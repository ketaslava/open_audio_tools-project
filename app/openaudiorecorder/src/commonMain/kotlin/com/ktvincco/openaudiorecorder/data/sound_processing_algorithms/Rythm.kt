package com.ktvincco.openaudiorecorder.data.sound_processing_algorithms

import com.ktvincco.openaudiorecorder.Settings


fun calculateVoiceRythm(vadGraph: FloatArray): Float {

    // Settings
    val processingWindowDurationSec = 8
    val minDataSize = 8

    // Check input
    if (vadGraph.isEmpty()) { return -1F }

    // Calculate window size
    var processingWindowSize = ((1F / Settings.getProcessingSampleDurationSec()) *
            processingWindowDurationSec).toInt() - 1
    if (vadGraph.size <= processingWindowSize) {
        processingWindowSize = vadGraph.size - 1
    }

    // Corp data, and check size
    val data = vadGraph.copyOfRange(
        (vadGraph.size - 1) - processingWindowSize, vadGraph.size - 1)
    if (data.size < minDataSize) { return -1F }

    // Count vad changes
    var bits = 0
    var currentBit = false
    for (vad in data) {
        if (vad > 0.5F && !currentBit) {
            bits += 1
            currentBit = true
        }
        if (vad < 0.5F && currentBit) {
            currentBit = false
        }
    }

    // Calculate BPM
    val time = (processingWindowSize * Settings.getProcessingSampleDurationSec()) / 60F
    return bits.toFloat() / time
}