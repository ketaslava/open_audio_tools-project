package com.ktvincco.openaudiotools.data.sound_processing_algorithms

import com.ktvincco.openaudiotools.Configuration


fun calculateVoiceClarity(loudnessGraph: FloatArray, vadGraph: FloatArray): Float {

    // Configuration
    val processingWindowDurationSec = 16

    // Calculate window size
    var processingWindowSize = ((1F / Configuration.getProcessingSampleDurationSec()) *
            processingWindowDurationSec).toInt() - 1
    if (loudnessGraph.size <= processingWindowSize) {
        processingWindowSize = loudnessGraph.size - 1
    }

    // Crop and split data in to voice and noise
    var voice = floatArrayOf()
    var noise = floatArrayOf()

    for (i in 0..processingWindowSize) {
        val index = (loudnessGraph.size -1) - i

        if(vadGraph[index] > 0.5) {
            voice += loudnessGraph[index]
        } else {
            noise += loudnessGraph[index]
        }
    }

    // Check
    if (voice.isEmpty() || noise.isEmpty()) { return -1F }

    // Calculate averages
    val avgVoice = voice.average().toFloat()
    val avgNoise = noise.average().toFloat()

    // Check
    if (avgVoice == 0F || avgNoise == 0F) { return -1F }
    if (avgNoise > avgVoice) { return 0F }

    // Calculate clarity
    return 1F - (avgNoise / avgVoice)
}