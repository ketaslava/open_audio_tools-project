package com.ktvincco.openaudiotools.data

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import com.ktvincco.openaudiotools.Configuration


class AndroidAudioRecorder: AudioRecorder {

    // Configuration
    private val sampleRate = Configuration.getSampleRate()

    // Variables
    private var audioRecord: AudioRecord? = null
    private var isRecording = false
    private var bufferSize = AudioRecord.getMinBufferSize(
        sampleRate,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )
    // bufferSize ≈ sampleRate × channels × bytesPerSample × 0.2
    private var buffer200ms = (sampleRate.toFloat() * 1F * 2F * 0.2F).toInt()
    private var recordingThread: Thread? = null
    private var dataCallback: (value: ShortArray) -> Unit = {}

    // Buffer size override in care of getMinBufferSize error
    init {
        if (bufferSize <= 0) {
            bufferSize = buffer200ms
        }
    }


    override fun setDataCallback(callback: (value: ShortArray) -> Unit) {
        dataCallback = callback
    }


    @SuppressLint("MissingPermission")
    override fun startRecording() {

        // Check recording
        if (isRecording) { return }

        // Initialize recorder
        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )

        // Check for critical EXC
        if (audioRecord?.state != AudioRecord.STATE_INITIALIZED) {
            throw IllegalStateException("AudioRecord is not initialized")
        }

        // Set state
        isRecording = true

        // Start recorder
        audioRecord?.startRecording()

        // Start thread
        recordingThread = Thread {
            val buffer = ShortArray(bufferSize / 2) // Buffer
            while (isRecording) {
                val readBytes = audioRecord?.read(buffer, 0, buffer.size) ?: 0
                if (readBytes > 0) {
                    // Send new data to the data callback
                    dataCallback.invoke(buffer)
                }
            }
        }
        recordingThread?.start()
    }


    override fun stopRecording() {

        // Chock state
        if (!isRecording) { return }

        // Sot state
        isRecording = false

        // Stop recorder
        audioRecord?.release()
        audioRecord = null

        // Stop thread
        recordingThread?.join()
        recordingThread = null
    }
}