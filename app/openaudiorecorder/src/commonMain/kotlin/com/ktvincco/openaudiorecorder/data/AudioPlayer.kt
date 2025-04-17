package com.ktvincco.openaudiorecorder.data

interface AudioPlayer {
    fun playAudioFromRawData(rawData: FloatArray)
    fun stop()
    fun setPositionCallback(callback: (isPlaying: Boolean, position: Int) -> Unit)
}