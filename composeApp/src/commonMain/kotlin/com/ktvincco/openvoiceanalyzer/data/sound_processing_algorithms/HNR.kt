package com.ktvincco.openvoiceanalyzer.data.sound_processing_algorithms


fun calculateHarmonicToNoiseRatio(spectrumInHz: FloatArray): Float {

    // Settings
    val voiceHzMin = 50

    // Calculate values
    val maxNoiseValue = spectrumInHz.copyOfRange(
        0, voiceHzMin).maxOrNull() ?: 0F
    val maxVoiceValue = spectrumInHz.copyOfRange(
        voiceHzMin, spectrumInHz.size - 1).maxOrNull() ?: 0F

    // Check values
    if (maxVoiceValue == 0F) { return 1F }
    if (maxVoiceValue < maxNoiseValue) { return 0F }

    // Calculate
    return 1F - (maxNoiseValue / maxVoiceValue)
}