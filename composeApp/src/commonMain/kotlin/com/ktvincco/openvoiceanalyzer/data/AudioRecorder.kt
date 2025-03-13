package com.ktvincco.openvoiceanalyzer.data

interface AudioRecorder {
    fun setDataCallback(callback: (value: ShortArray) -> Unit)
    fun startRecording()
    fun stopRecording()
}