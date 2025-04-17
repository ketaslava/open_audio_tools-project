package com.ktvincco.openaudiotools.data.sound_processing_algorithms

import com.ktvincco.openaudiotools.Settings


fun calculateVoicePausesDuration(vadGraph: FloatArray): Float {

    // Settings
    val processingWindowDurationSec = 16
    val minDataSize = 32
    val pauseMinDurationSec = 0.24F

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

    // Count pauses duration
    val pauseMinDuration = (
            pauseMinDurationSec / Settings.getProcessingSampleDurationSec()).toInt()
    var pausesDuration = 0
    var currentFrameState = false
    var currentFrameDuration = 0

    for (vad in data) {

        // Silence ended
        if (vad > 0.5F && !currentFrameState) {
            currentFrameState = true

            // If it is a pause, add time
            if (currentFrameDuration > pauseMinDuration) {
                pausesDuration += currentFrameDuration
            }
        }

        // Silence started
        if (vad < 0.5F && currentFrameState) {
            currentFrameState = false

            // Reset timer
            currentFrameDuration = 0
        }

        // Enough silence time passed
        if (!currentFrameState && currentFrameDuration > pauseMinDuration * 2) {
            pausesDuration += pauseMinDuration
            currentFrameDuration -= pauseMinDuration
        }

        // Count frames
        currentFrameDuration += 1
    }

    // Calculate pauses fraction from time
    return pausesDuration.toFloat() / data.size.toFloat()
}