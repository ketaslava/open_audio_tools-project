package com.ktvincco.openvoiceanalyzer.domain

import com.ktvincco.openaudiotools.data.AudioPlayer
import com.ktvincco.openaudiotools.data.AudioRecorder
import com.ktvincco.openaudiotools.data.Database
import com.ktvincco.openaudiotools.data.Logger
import com.ktvincco.openaudiotools.data.PermissionController
import com.ktvincco.openaudiotools.data.SoundFile
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.RecordingQuality
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateActiveFirstAndSecondFormant
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateBandEnergyRatioHigh
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateBandEnergyRatioLow
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateBandEnergyRatioMedium
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateEnergySpectrumInHz
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateFirstAndSecondFormant
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateH1H2EnergyBalance
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateHLRatio
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateHarmonicToNoiseRatio
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateLoudness
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculatePitch
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateRecordingQuality
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateSpectralCentroid
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateSpectralSpread
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateSpectralTilt
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateVAD
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateVoiceClarity
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateVoiceEnergy
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateVoiceJitter
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateVoicePausesDuration
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateVoiceProsody
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateVoiceRythm
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateVoiceShimmer
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.calculateVoiceWeight
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.getVoiceSpectrumInHz
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler


class AudioProcessor (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler,
    private val logger: Logger,
    private val permissionController: PermissionController,
    private val audioRecorder: AudioRecorder,
    private val database: Database,
    private val soundFile: SoundFile,
    private val audioPlayer: AudioPlayer,
) {

    // Data
    private var pointerPosition = 1F
    private var dataSize = 0


    // Spectrogram Data
    private var spectrogramData: MutableMap<String, Array<FloatArray>> = mutableMapOf()
    private fun addToSpectrogramData(name: String, newValue: FloatArray) {
        var data = spectrogramData[name] ?: arrayOf()
        data += newValue
        spectrogramData[name] = data
    }
    private fun getSpectrogramData(name: String): Array<FloatArray> {
        return spectrogramData[name] ?: arrayOf()
    }


    // Graph Data
    private var graphData: MutableMap<String, FloatArray> = mutableMapOf()
    private fun addToGraphData(name: String, newValue: Float) {
        var data = graphData[name] ?: floatArrayOf()
        data += newValue
        graphData[name] = data
    }
    private fun getGraphData(name: String): FloatArray {
        return graphData[name] ?: floatArrayOf()
    }


    // Display Data
    private var displayData: MutableMap<String, Float> = mutableMapOf()


    // Quality
    private var recordingQuality = Pair(arrayOf(""), RecordingQuality())
    private var recordingIssueArray: Array<String> = arrayOf()


    // Private


    private fun getCursorPosition(): Int {
        return ((dataSize - 1).toFloat() * pointerPosition).toInt()
    }


    private fun updateDisplaysData() {
        for (key in graphData.keys) {
            displayData[key] = graphData[key]!![getCursorPosition()]
        }
    }


    private fun updateDisplaysUi() {
        modelData.setDisplayData(displayData)
    }


    fun updateUi() {

        // Update Spectrograms
        modelData.setSpectrogramData(spectrogramData)

        // Update Graphs
        modelData.setGraphData(graphData)

        // Update displays
        updateDisplaysUi()

        // Quality
        modelData.setRecordingQuality(recordingQuality.second)

        // Cleanup
        database.forceGC()
    }


    // Optimization while development tool
    //private var startTime = epochMillis()
    private fun measureCheckpoint(name: String) {
        //println("Checkpoint <$name> is <${epochMillis() - startTime}> ms")
        //startTime = epochMillis()
    }
    
    
    // Public


    fun processData(currentSample: FloatArray, isUpdateUi: Boolean) {

        // Calculate data
        measureCheckpoint("Loop")
        
        // From sample
        val loudness = calculateLoudness(currentSample)
        measureCheckpoint("Loudness")

        // SpectrumInfo
        val spectrumInHz = getVoiceSpectrumInHz(currentSample)
        measureCheckpoint("SpectrumInfo")
        val energySpectrumInHz = calculateEnergySpectrumInHz(spectrumInHz)
        measureCheckpoint("Energy Spectrum")

        // VAD
        val vad = calculateVAD(spectrumInHz)
        measureCheckpoint("VAD")

        // Voice related
        val pitch = calculatePitch(spectrumInHz, vad)
        measureCheckpoint("Pitch")
        val harmonicToNoiseRatio = calculateHarmonicToNoiseRatio(spectrumInHz)
        measureCheckpoint("HNR")
        val firstAndSecondFormant = calculateFirstAndSecondFormant(energySpectrumInHz)
        val activeFirstAndSecondFormant = calculateActiveFirstAndSecondFormant(
            firstAndSecondFormant, vad)
        measureCheckpoint("Formats")
        val energy = calculateVoiceEnergy(energySpectrumInHz, pitch, vad)
        measureCheckpoint("Energy")
        val h1h2EnergyBalance = calculateH1H2EnergyBalance(spectrumInHz, pitch, vad)
        measureCheckpoint("H1 H2 Energy Balance")
        val voiceWeight = calculateVoiceWeight(spectrumInHz, pitch, firstAndSecondFormant, vad)
        measureCheckpoint("VoiceWeight")

        // Static
        val spectralCentroid = calculateSpectralCentroid(spectrumInHz)
        measureCheckpoint("Spectral Centroid")
        val spectralTilt = calculateSpectralTilt(spectrumInHz)
        measureCheckpoint("Spectral Tilt")
        val spectralSpread = calculateSpectralSpread(spectrumInHz)
        measureCheckpoint("Spectral Spread")
        val bandEnergyRatioLow = calculateBandEnergyRatioLow(spectrumInHz)
        measureCheckpoint("BER Low")
        val bandEnergyRatioMedium = calculateBandEnergyRatioMedium(spectrumInHz)
        measureCheckpoint("BER Medium")
        val bandEnergyRatioHigh = calculateBandEnergyRatioHigh(spectrumInHz)
        measureCheckpoint("BER High")
        val hlRatio = calculateHLRatio(bandEnergyRatioHigh, bandEnergyRatioLow)
        measureCheckpoint("H/L Ratio")

        // Semi dynamic
        val jitter = calculateVoiceJitter(getGraphData("Pitch"))
        measureCheckpoint("Jitter")
        val shimmer = calculateVoiceShimmer(
            getGraphData("Pitch"), getSpectrogramData("SpectrogramInHz"))
        measureCheckpoint("Shimmer")

        // Dynamic
        val prosody = calculateVoiceProsody(getGraphData("Pitch"))
        measureCheckpoint("Prosody")
        val rythm = calculateVoiceRythm(getGraphData("VAD"))
        measureCheckpoint("Rythm")
        val clarity = calculateVoiceClarity(
            getGraphData("Loudness"), getGraphData("VAD"))
        measureCheckpoint("Clarity")
        val pausesDuration = calculateVoicePausesDuration(getGraphData("VAD"))
        measureCheckpoint("Pauses Duration")

        // Quality
        recordingQuality = calculateRecordingQuality(recordingIssueArray, loudness)

        // Update data

        // Update spectrograms
        addToSpectrogramData("SpectrogramInHz", spectrumInHz)
        addToSpectrogramData("EnergySpectrogramInHz", energySpectrumInHz)

        // Update graphs
        addToGraphData("Loudness", loudness)
        addToGraphData("Pitch", pitch)
        addToGraphData("FirstFormant", firstAndSecondFormant.first)
        addToGraphData("SecondFormant", firstAndSecondFormant.second)
        addToGraphData("ActiveFirstFormant", activeFirstAndSecondFormant.first)
        addToGraphData("ActiveSecondFormant", activeFirstAndSecondFormant.second)
        addToGraphData("Energy", energy)
        addToGraphData("H1H2EnergyBalance", h1h2EnergyBalance)
        addToGraphData("HarmonicToNoiseRatio", harmonicToNoiseRatio)
        addToGraphData("BandEnergyRatioLow", bandEnergyRatioLow)
        addToGraphData("BandEnergyRatioMedium", bandEnergyRatioMedium)
        addToGraphData("BandEnergyRatioHigh", bandEnergyRatioHigh)
        addToGraphData("HLRatio", hlRatio)
        addToGraphData("VoiceWeight", voiceWeight)
        addToGraphData("VAD", vad)
        addToGraphData("SpectralCentroid", spectralCentroid)
        addToGraphData("SpectralTilt", spectralTilt)
        addToGraphData("SpectralSpread", spectralSpread)
        addToGraphData("Jitter", jitter)
        addToGraphData("Shimmer", shimmer)
        addToGraphData("Prosody", prosody)
        addToGraphData("Rythm", rythm)
        addToGraphData("Clarity", clarity)
        addToGraphData("PausesDuration", pausesDuration)

        // Quality
        recordingIssueArray += recordingQuality.first

        // Update state
        dataSize += 1

        // Update UI

        if (isUpdateUi) {
            updateDisplaysData()
            updateUi()
        }
    }


    fun reset() {

        // Reset spectrograms
        spectrogramData = mutableMapOf()

        // Reset graphs
        graphData = mutableMapOf()

        // Reset displays
        displayData = mutableMapOf()

        // Reset quality
        recordingIssueArray = arrayOf()

        // Reset state
        dataSize = 0

        // Reset UI
        updateUi()
    }


    fun rewindDisplaysTo(targetPointerPosition: Float) {
        pointerPosition = targetPointerPosition
        updateDisplaysData()
        updateDisplaysUi()
    }
}