package com.ktvincco.openvoiceanalyzer.data

import com.ktvincco.openvoiceanalyzer.Settings
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine
import kotlin.concurrent.thread
import kotlin.math.min


class DesktopAudioPlayer: AudioPlayer {

    private var isPlaying = false
    private var playbackThread: Thread? = null
    private var positionCallback: ((isPlaying: Boolean, position: Int) -> Unit)? = null


    override fun playAudioFromRawData(rawData: FloatArray) {
        stop()

        // FloatArray to (PCM 16-bit) ShortArray
        val pcmData = rawData.map { (it * Short.MAX_VALUE).toInt().toShort() }.toShortArray()

        // Configure audio
        val sampleRate = Settings.getSampleRate()
        val channels = 1 // Mono
        val sampleSizeInBits = 16 // PCM 16-bit
        val bigEndian = false // Little-endian
        val format = AudioFormat(
            sampleRate.toFloat(),
            sampleSizeInBits,
            channels,
            true, // Signed
            bigEndian
        )

        // Combine
        val lineInfo = DataLine.Info(SourceDataLine::class.java, format)

        // Check
        if (!AudioSystem.isLineSupported(lineInfo)) {
            throw IllegalArgumentException("Unsupported audio format")
        }

        // Set state
        val buffer = ByteArray(Settings.getAudioBufferSize()) // Data translation buffer
        isPlaying = true

        // Launch playback thread
        playbackThread = thread {
            val line = AudioSystem.getLine(lineInfo) as SourceDataLine
            try {
                line.open(format)
                line.start()

                var offset = 0
                while (isPlaying && offset < pcmData.size) {

                    // Calculate next data package
                    val length = min(buffer.size / 2, pcmData.size - offset)
                    for (i in 0 until length) {
                        val value = pcmData[offset + i]
                        buffer[i * 2] = (value.toInt() and 0xFF).toByte()
                        buffer[i * 2 + 1] = ((value.toInt() shr 8) and 0xFF).toByte()
                    }

                    // Write data
                    line.write(buffer, 0, length * 2)

                    // Move forward
                    offset += length

                    // Send information about pointer position
                    positionCallback?.invoke(isPlaying, offset)
                }

                // Send information about pointer position
                positionCallback?.invoke(false, 0)

            } finally {
                // Cleanup
                line.drain()
                line.close()
            }
        }
    }


    override fun stop() {
        if (!isPlaying) return

        isPlaying = false
        playbackThread?.join()
        playbackThread = null
    }


    override fun setPositionCallback(callback: (isPlaying: Boolean, position: Int) -> Unit) {
        positionCallback = callback
    }
}