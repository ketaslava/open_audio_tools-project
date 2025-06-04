package com.ktvincco.openaudiotools.data

interface AudioRecorder {
    fun setDataCallback(callback: (value: ShortArray) -> Unit)
    fun startRecording()
    fun stopRecording()
}