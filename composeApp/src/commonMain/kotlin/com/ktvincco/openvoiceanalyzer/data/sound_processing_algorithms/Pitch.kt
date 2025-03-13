package com.ktvincco.openvoiceanalyzer.data.sound_processing_algorithms


fun calculatePitch(spectrumInHz: FloatArray, vad: Float): Float {

    // Check
    if (vad < 0.5F) { return -1F }

    // Settings
    val harmonicsCount = 8
    val voiceHzMin = 50
    val voiceHzMax = 500

    // Optimization Settings
    val offsetStep = 4
    val gapStep = 4

    // Calculate 1 harmonic as a pitch
    val sumByOffset = FloatArray(voiceHzMax + 1)
    for (offset in voiceHzMin..voiceHzMax step offsetStep) {
        for (gap in voiceHzMin..voiceHzMax step gapStep) {
            for (harmonic in 0..<harmonicsCount) {
                val index = offset + (gap * harmonic)
                if (index < spectrumInHz.size) {
                    sumByOffset[offset] += spectrumInHz[index]
                }
            }
        }
    }
    val pitch = sumByOffset.toList().indexOf(sumByOffset.maxOrNull())

    // Return a result
    return pitch.toFloat()
}