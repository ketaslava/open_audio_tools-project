package com.ktvincco.openaudiotools.data

import com.ktvincco.openaudiotools.resampleAudioData
import java.io.File
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.min


class AndroidSoundFile: SoundFile {

    override fun writeSoundToFile(filePath: String, audioData: FloatArray, sampleRate: Int) {

        val file = File(filePath)

        file.outputStream().use { outputStream ->

            // Convert FloatArray to (PCM 16-bit) ShortArray
            val pcmData = audioData.map { (it * Short.MAX_VALUE).toInt().toShort() }.toShortArray()

            // Configure
            val numChannels = 1
            val bitsPerSample = 16
            val byteRate = sampleRate * numChannels * bitsPerSample / 8
            val blockAlign = numChannels * bitsPerSample / 8

            // Write WAV header
            val header = ByteBuffer.allocate(44).apply {
                order(ByteOrder.LITTLE_ENDIAN)
                put("RIFF".toByteArray()) // ChunkID
                putInt(36 + pcmData.size * 2) // ChunkSize
                put("WAVE".toByteArray()) // Format
                put("fmt ".toByteArray()) // Sub chunk 1 ID
                putInt(16) // Sub chunk 1 Size
                putShort(1) // AudioFormat (PCM)
                putShort(numChannels.toShort()) // NumChannels
                putInt(sampleRate) // SampleRate
                putInt(byteRate) // ByteRate
                putShort(blockAlign.toShort()) // BlockAlign
                putShort(bitsPerSample.toShort()) // BitsPerSample
                put("data".toByteArray()) // Sub chunk 2 ID
                putInt(pcmData.size * 2) // Sub chunk 2 Size
            }.array()
            outputStream.write(header)

            // Write PCM data
            val pcmBytes = ByteBuffer.allocate(pcmData.size * 2).apply {
                order(ByteOrder.LITTLE_ENDIAN)
                pcmData.forEach { putShort(it) }
            }.array()
            outputStream.write(pcmBytes)
        }
    }


    override fun readSoundFromFile(filePath: String, targetSampleRate: Int): FloatArray {

        // Open file
        val file = RandomAccessFile(filePath, "r")
        val fileChannel = file.channel

        // Read WAV header (first 44 bytes)
        val headerBuffer = ByteBuffer.allocate(44).apply { order(ByteOrder.LITTLE_ENDIAN) }
        fileChannel.read(headerBuffer)
        headerBuffer.flip()

        // Check RIFF/WAVE signature
        val riff = ByteArray(4).also { headerBuffer.get(it) }
        if (!riff.contentEquals("RIFF".toByteArray())) throw IllegalArgumentException("Invalid WAV file")

        // Get sample rate
        headerBuffer.position(24)
        val sourceSampleRate = headerBuffer.int

        // Get data size
        headerBuffer.position(40)
        val dataSize = headerBuffer.int

        // Read PCM data
        val pcmBuffer = ByteBuffer.allocate(dataSize).apply { order(ByteOrder.LITTLE_ENDIAN) }
        fileChannel.read(pcmBuffer)
        pcmBuffer.flip()

        // Transform PCM 16 bit to FloatArray
        val shortArray = ShortArray(dataSize / 2) { pcmBuffer.short }
        val floatArray = shortArray.map { it.toFloat() / Short.MAX_VALUE }.toFloatArray()

        // Close file
        file.close()

        // If sample rate is equal to working sample rate, we gonna use it like this
        if (sourceSampleRate == targetSampleRate) {
            return floatArray
        }

        // Or else we gonna resample the data
        return resampleAudioData(floatArray, sourceSampleRate, targetSampleRate)
    }

}
