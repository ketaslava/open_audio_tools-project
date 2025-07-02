package com.ktvincco.openaudiotools.data

import com.ktvincco.openaudiotools.Configuration

interface SoundFile {
    fun writeSoundToFile(
        filePath: String, audioData: FloatArray, sampleRate: Int = Configuration.getSampleRate())
    fun readSoundFromFile(
        filePath: String, targetSampleRate: Int = Configuration.getSampleRate()): FloatArray
}