package com.ktvincco.openvoiceanalyzer.data

import java.io.File
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.ByteOrder


class DesktopSoundFile : SoundFile {

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

            // Write WAV Headline
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


    override fun readSoundFromFile(filePath: String, sampleRate: Int): FloatArray {
        RandomAccessFile(filePath, "r").use { file ->
            val fileChannel = file.channel

            // Read WAV header
            val headerBuffer = ByteBuffer.allocate(44).apply { order(ByteOrder.LITTLE_ENDIAN) }
            fileChannel.read(headerBuffer)
            headerBuffer.flip()

            // Check
            /*val chunkId = ByteArray(4).apply { headerBuffer.get(this) }
            require(String(chunkId) == "RIFF") { "Invalid WAV file: RIFF header missing" }

            headerBuffer.position(22)
            val numChannels = headerBuffer.short
            require(numChannels == 1.toShort()) { "Only mono audio is supported" }

            val fileSampleRate = headerBuffer.int
            require(fileSampleRate == sampleRate) {
                "Sample rate mismatch: expected $sampleRate, found $fileSampleRate" }*/

            // Configure reader
            headerBuffer.position(40) // Skip to Sub chunk 2 Size
            val dataSize = headerBuffer.int

            // Read PCM data
            val pcmBuffer = ByteBuffer.allocate(dataSize).apply { order(ByteOrder.LITTLE_ENDIAN) }
            fileChannel.read(pcmBuffer)
            pcmBuffer.flip()

            // Convert (PCM 16-bit) ShortArray to (0.0 - 1.0) FloatArray
            val shortArray = ShortArray(dataSize / 2) { pcmBuffer.short }
            return shortArray.map { it.toFloat() / Short.MAX_VALUE }.toFloatArray()
        }
    }
}