package com.ktvincco.openaudiotools.data

import com.ktvincco.openaudiotools.Settings
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.TargetDataLine


class DesktopAudioRecorder : AudioRecorder {
    private var isRecording = false
    private var dataCallback: (value: ShortArray) -> Unit = {}

    override fun setDataCallback(callback: (value: ShortArray) -> Unit) {
        dataCallback = callback
    }

    override fun startRecording() {
        if (isRecording) return

        // Setup recorder
        val format = AudioFormat(
            Settings.getSampleRate().toFloat(),
            16,
            1,
            true,
            true
        )
        val info = DataLine.Info(TargetDataLine::class.java, format)

        // Calculate the line buffer size (not the read buffer)
        val sampleRate = Settings.getSampleRate().toFloat()
        val bytesPerSample = 2F  // 16‑bit = 2 bytes
        val channels       = 1F
        val bufferMs       = 200F
        val lineBufferSize = (sampleRate * channels * bytesPerSample * (bufferMs / 1000F)).toInt()

        // Choose the source
        val line = getTargetDataLine(info, format)

        // Open the line
        line.open(format, lineBufferSize)
        line.start()

        // throw away any already‑queued samples
        val available = line.available()
        if (available > 0) {
            val tmp = ByteArray(available.coerceAtMost(lineBufferSize))
            line.read(tmp, 0, tmp.size)
        }

        // Set state
        isRecording = true

        // Run listener thread
        Thread {
            val buffer = ByteArray(Settings.getAudioBufferSize())
            while (isRecording) {
                val bytesRead = line.read(buffer, 0, buffer.size)
                if (bytesRead > 0) {
                    val shortBuffer = ShortArray(bytesRead / 2) { i ->
                        ((buffer[i * 2].toInt() shl 8) or (buffer[i * 2 + 1].toInt() and 0xFF)).toShort()
                    }
                    dataCallback(shortBuffer)
                }
            }
            line.stop()
            line.close()
        }.start()
    }

    override fun stopRecording() {
        isRecording = false
    }

    private fun getTargetDataLine(info: DataLine.Info, format: AudioFormat): TargetDataLine {
        return try {
            if (isLinux()) {
                val mixerInfo = AudioSystem.getMixerInfo().find { it.name.contains("PulseAudio", ignoreCase = true) }
                if (mixerInfo != null) {
                    val mixer = AudioSystem.getMixer(mixerInfo)
                    return mixer.getLine(info) as TargetDataLine
                }
            }
            // Use system source by default
            AudioSystem.getLine(info) as TargetDataLine
        } catch (e: Exception) {
            throw RuntimeException("Audio source is not found", e)
        }
    }

    private fun isLinux(): Boolean {
        return System.getProperty("os.name").lowercase().contains("linux")
    }
}