package com.ktvincco.openaudiorecorder.data

interface AudioRecorder {
    fun setDataCallback(callback: (value: ShortArray) -> Unit)
    fun startRecording()
    fun stopRecording()
}