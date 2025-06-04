package com.ktvincco.openaudiotools.data.sound_processing_algorithms


fun calculateSpectralSpread(spectrumInHz: FloatArray): Float {

    // Normalize input
    val maxMagnitude = spectrumInHz.maxOrNull() ?: 1F
    if (maxMagnitude <= 0F) return 0F
    val normalizedSpectrum = spectrumInHz.map { it / maxMagnitude }

    // Calculate Spectral Centroid
    val energySum = normalizedSpectrum.sum()
    if (energySum == 0F) return 0F

    val spectralCentroid = normalizedSpectrum
        .mapIndexed { index, magnitude -> index * magnitude }
        .sum() / energySum

    // Calculate Spectral Spread
    val spectralSpread = kotlin.math.sqrt(
        normalizedSpectrum.mapIndexed { index, magnitude ->
            val deviation = index - spectralCentroid
            (deviation * deviation) * magnitude
        }.sum() / energySum
    )

    // Normalize output to [0, 1]
    return (spectralSpread / (spectrumInHz.size / 2F)).coerceIn(0F, 1F)
}