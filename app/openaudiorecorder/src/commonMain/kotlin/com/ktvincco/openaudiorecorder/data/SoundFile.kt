package com.ktvincco.openaudiorecorder.data

interface SoundFile {
    fun writeSoundToFile(filePath: String, audioData: FloatArray, sampleRate: Int = 44100)
    fun readSoundFromFile(filePath: String, sampleRate: Int = 44100): FloatArray
}