package com.ktvincco.openaudiorecorder.data.sound_processing_algorithms

import kotlin.math.abs


fun getFreqEnergy (spectrumInHz: FloatArray, freq: Float, sideSpread: Int): Float {
    var offset = -sideSpread
    var energy = 0F
    while (offset < sideSpread) {
        energy += spectrumInHz[freq.toInt() + offset]
        offset += 1
    }
    return energy
}


fun calculateH1H2EnergyBalance (spectrumInHz: FloatArray, pitch: Float, vad: Float): Float {

    // Check
    if (vad < 0.5 || (pitch * 2F).toInt() > spectrumInHz.size - 1) { return -10F }

    // Calculate values
    val sideSpread = pitch.toInt() / 2

    // Calculate energies
    val harmonic1Energy = getFreqEnergy(spectrumInHz, pitch, sideSpread)
    val harmonic2Energy = getFreqEnergy(spectrumInHz, pitch * 2F, sideSpread)

    // Calculate balance and return result
    return if (harmonic1Energy > harmonic2Energy) {
        1f - (harmonic2Energy / harmonic1Energy)
    } else {
        -1f + (harmonic1Energy / harmonic2Energy)
    }
}


fun findTheNearestHarmonic (pitch: Float, formant: Float): Float {

    // Check
    if (pitch <= 1F || formant > maxVoiceFreqHz.toFloat()) {
        return 0F
    }

    // Search
    var h = pitch
    var lastD = maxVoiceFreqHz.toFloat()
    while (true) {

        // Get distance between harmonic and formant
        val d = abs(formant - h)

        // If distance increased -> previous harmonic harmonic was nearest
        if (d > lastD) {
            return h - pitch
        }

        // Next harmonic
        lastD = d
        h += pitch
    }
}


fun calculateVoiceWeight (spectrumInHz: FloatArray, pitch: Float,
                          firstAndSecondFormant: Pair<Float, Float>, vad: Float): Float {

    // Check
    if (vad < 0.5 || (pitch * 2F).toInt() > spectrumInHz.size - 1) { return -1F }

    // Calculate values
    val sideSpread = pitch.toInt() / 2

    // Get energy of the first harmonic
    val harmonic1Energy = getFreqEnergy(spectrumInHz, pitch, sideSpread)

    // Get avg energy of the nearest harmonics of the first and second formants
    val hF1E = getFreqEnergy(spectrumInHz,
        findTheNearestHarmonic(pitch, firstAndSecondFormant.first), sideSpread)
    val hF2E = getFreqEnergy(spectrumInHz,
        findTheNearestHarmonic(pitch, firstAndSecondFormant.second), sideSpread)
    val formantHarmonicSum = hF1E + hF2E

    // Calculate balance and return result
    return if (formantHarmonicSum > harmonic1Energy) {
        1f - ((harmonic1Energy / formantHarmonicSum) / 2)
    } else {
        (formantHarmonicSum / harmonic1Energy) / 2
    }
}