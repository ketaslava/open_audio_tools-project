package com.ktvincco.openaudiorecorder.data.sound_processing_algorithms


fun calculateBandEnergyRatio(spectrum: FloatArray, startHz: Int, endHz: Int): Float {

    // Calculate total energy
    val totalEnergy = spectrum.map { it * it }.sum()
    if (totalEnergy == 0F) return 0F // Avoid division by zero

    // Calculate band energy
    val bandEnergy = spectrum.sliceArray(startHz until endHz).map { it * it }.sum()

    // Calculate Band Energy Ratio
    return bandEnergy / totalEnergy
}


fun calculateBandEnergyRatioLow(spectrumInHz: FloatArray): Float {
    return calculateBandEnergyRatio(spectrumInHz, 0, 300)
}


fun calculateBandEnergyRatioMedium(spectrumInHz: FloatArray): Float {
    return calculateBandEnergyRatio(spectrumInHz, 300, 1200)
}


fun calculateBandEnergyRatioHigh(spectrumInHz: FloatArray): Float {
    return calculateBandEnergyRatio(spectrumInHz, 1200, 4096)
}


fun calculateHLRatio (bandEnergyRatioHigh: Float, bandEnergyRatioLow: Float): Float {

    // Calculate balance and return result
    return if (bandEnergyRatioHigh > bandEnergyRatioLow) {
        1f - (bandEnergyRatioLow / bandEnergyRatioHigh)
    } else {
        -1f + (bandEnergyRatioHigh / bandEnergyRatioLow)
    }
}