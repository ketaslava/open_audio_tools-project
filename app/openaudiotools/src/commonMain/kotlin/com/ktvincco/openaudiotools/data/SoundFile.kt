package com.ktvincco.openaudiotools.data

import com.ktvincco.openaudiotools.Settings

interface SoundFile {
    fun writeSoundToFile(
        filePath: String, audioData: FloatArray, sampleRate: Int = Settings.getSampleRate())
    fun readSoundFromFile(
        filePath: String, sampleRate: Int = Settings.getSampleRate()): FloatArray
}