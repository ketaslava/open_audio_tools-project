package com.ktvincco.openaudiotools.data.sound_processing_algorithms


fun calculateSpectralCentroid(spectrumInHz: FloatArray): Float {

    // Check
    if (spectrumInHz.isEmpty()) return -1F

    // Search average of all values
    var weightedSum = 0F
    var energySum = 0F

    for (frequency in spectrumInHz.indices) {
        val energy = spectrumInHz[frequency]
        weightedSum += frequency * energy
        energySum += energy
    }

    // Check
    if (energySum == 0F) return 0F

    // Return result
    return weightedSum / energySum
}
