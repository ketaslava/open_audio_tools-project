package com.ktvincco.openvoiceanalyzer.data

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import com.ktvincco.openvoiceanalyzer.Settings


class AndroidAudioPlayer : AudioPlayer {

    private var audioTrack: AudioTrack? = null
    private var isPlaying = false
    private var playbackThread: Thread? = null
    private var positionCallback: ((isPlaying: Boolean, position: Int) -> Unit)? = null


    override fun playAudioFromRawData(rawData: FloatArray) {
        stop()

        // FloatArray to (PCM 16-bit) ShortArray
        val pcmData = rawData.map { (it * Short.MAX_VALUE).toInt().toShort() }.toShortArray()

        // Configure audio
        val sampleRate = Settings.getSampleRate()
        val channelConfig = AudioFormat.CHANNEL_OUT_MONO
        val audioFormat = AudioFormat.ENCODING_PCM_16BIT

        // Initialize AudioTrack thru Builder
        val minBufferSize = AudioTrack.getMinBufferSize(sampleRate, channelConfig, audioFormat)
        audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setSampleRate(sampleRate)
                    .setChannelMask(channelConfig)
                    .setEncoding(audioFormat)
                    .build()
            )
            .setBufferSizeInBytes(minBufferSize)
            .setTransferMode(AudioTrack.MODE_STREAM)
            .build()

        // Set state
        isPlaying = true
        audioTrack?.play()

        // Launch playback thread
        playbackThread = Thread {

            // Configure
            val bufferSize = Settings.getAudioBufferSize()
            var offset = 0

            // Write data
            while (isPlaying && offset + 1 < pcmData.size) {

                // Calculate and write
                val length = minOf(bufferSize, pcmData.size - offset)
                audioTrack?.write(pcmData, offset, length, AudioTrack.WRITE_BLOCKING)

                // Move forward
                offset += length

                // Send information about pointer position
                positionCallback?.invoke(isPlaying, offset)
            }

            // Send information about pointer position
            positionCallback?.invoke(false, 0)
        }
        playbackThread?.start()
    }


    override fun stop() {
        if (!isPlaying) { return }

        isPlaying = false
        audioTrack?.stop()
        audioTrack?.release()
        audioTrack = null
        playbackThread?.join()
        playbackThread = null
    }


    override fun setPositionCallback(callback: (isPlaying: Boolean, position: Int) -> Unit) {
        positionCallback = callback
    }
}

