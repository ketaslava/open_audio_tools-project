package com.ktvincco.openvoiceanalyzer.data.sound_processing_algorithms


fun calculateEnergySpectrumInHz(spectrumInHz: FloatArray): FloatArray {

    // Get data
    var spectrum = spectrumInHz

    // ApplyFilters
    spectrum = applyHanningWindow(spectrum)
    spectrum = applyWindowSmooth(spectrum, 512, 4)

    // Normalize
    val maxMagnitude = spectrum.maxOrNull() ?: 1F
    spectrum = spectrum.map { it / maxMagnitude }.toFloatArray()

    // Return a result when the sound is clear
    return spectrum
}


fun calculateFirstAndSecondFormant(energySpectrumInHz: FloatArray): Pair<Float, Float> {

    // Check
    if (energySpectrumInHz.isEmpty()) {
        return Pair(-1F, -1F)
    }

    // Settings
    val searchHzMin = 50
    val minGap = 512

    // Optimization Settings
    val f1Step = 1
    val gapStep = 1

    // Calculate first and second formant
    var maxSum = 0F
    var maxSumF1 = 0
    var maxSumF2 = 0

    for (f1 in searchHzMin..<energySpectrumInHz.size step f1Step) {
        for (f2Offset in minGap..<energySpectrumInHz.size - f1 step gapStep) {
            val f2 = f1 + f2Offset
            val sum = energySpectrumInHz[f1] + energySpectrumInHz[f2]
            if (sum > maxSum) {
                maxSum = sum
                maxSumF1 = f1
                maxSumF2 = f2
            }
        }
    }
    val firsAndSecondFormant = Pair(maxSumF1.toFloat(), maxSumF2.toFloat())

    // Return a result
    return firsAndSecondFormant
}


fun calculateActiveFirstAndSecondFormant(
    firsAndSecondFormant: Pair<Float, Float>, vad: Float): Pair<Float, Float> {

    // Check VAD
    if (vad < 0.5F) { return Pair(-1F, -1F) }

    // Return a result
    return firsAndSecondFormant
}


fun calculateVoiceEnergy(energySpectrumInHz: FloatArray, pitch: Float, vad: Float): Float {

    // Check
    if (vad < 0.5F) { return -1F }

    // Return a result
    return energySpectrumInHz[pitch.toInt()]
}