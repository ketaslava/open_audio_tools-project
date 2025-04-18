package com.ktvincco.openvoiceanalyzer.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import com.ktvincco.openaudiotools.presentation.BasicModelData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ModelData: BasicModelData() {

    // Default pages
    fun openVoiceChangeGuidelinesPage() { super._currentPage.value = "VoiceChangeGuidelines" }

    // Mode pages
    fun openAllInfoPage() { super._currentPage.value = "AllInfo" }
    fun openSpectrumInfoPage() { super._currentPage.value = "SpectrumInfo" }
    fun openReadingPage() { super._currentPage.value = "Reading" }
    fun openSpeakerVoicePage() { super._currentPage.value = "SpeakerVoice" }
    fun openSingingPage() { super._currentPage.value = "Singing" }
    fun openPitchAndResonancePage() { super._currentPage.value = "PitchAndResonance" }
    fun openVoiceSmoothnessPage() { super._currentPage.value = "VoiceSmoothness" }
    fun openFemaleVoicePage() { super._currentPage.value = "FemaleVoice" }
    fun openFemaleVoiceResonancePage() { super._currentPage.value = "FemaleVoiceResonance" }
    fun openMaleVoicePage() { super._currentPage.value = "MaleVoice" }
    fun openMaleVoiceResonancePage() { super._currentPage.value = "MaleVoiceResonance" }


    // Recording control layout
    private val _recordingControlLayout = MutableStateFlow("ReadyToRecording")
    val recordingControlLayout: StateFlow<String> = _recordingControlLayout
    fun setRecordingControlLayoutAsReadyToRecording() {
        _recordingControlLayout.value = "ReadyToRecording"
    }

    fun setRecordingControlLayoutAsRecording() {
        _recordingControlLayout.value = "Recording"
    }

    fun setRecordingControlLayoutAsDeleteSaveOrPlay() {
        _recordingControlLayout.value = "DeleteSaveOrPlay"
    }

    fun setRecordingControlLayoutAsPlayer() {
        _recordingControlLayout.value = "Player"
    }


    // Spectrogram Data
    private val _spectrogramData = mutableStateMapOf<String, Array<FloatArray>>()
    fun setSpectrogramData(newValue: Map<String, Array<FloatArray>>) {
        _spectrogramData.clear()
        _spectrogramData.putAll(newValue)
    }
    @Composable
    fun getSpectrogramData(name: String): Array<FloatArray> {
        return _spectrogramData[name] ?: arrayOf()
    }


    // Graphs Data
    private val _graphData = mutableStateMapOf<String, FloatArray>()
    fun setGraphData(newValue: Map<String, FloatArray>) {
        _graphData.clear()
        _graphData.putAll(newValue)
    }
    @Composable
    fun getGraphData(name: String): FloatArray {
        return _graphData[name] ?: floatArrayOf()
    }


    // Displays Data
    private val _displayData = mutableStateMapOf<String, Float>()
    fun setDisplayData(newValue: Map<String, Float>) {
        _displayData.clear()
        _displayData.putAll(newValue)
    }
    @Composable
    fun getDisplayData(name: String): Float {
        return _displayData[name] ?: 0f
    }


    // Help menu
    private val _helpMenuState = MutableStateFlow(false)
    val helpMenuState: StateFlow<Boolean> = _helpMenuState
    fun setHelpMenuState(newValue: Boolean) {
        _helpMenuState.value = newValue
    }
    private val _helpMenuParameterId = MutableStateFlow("Pitch")
    val helpMenuParameterId: StateFlow<String> = _helpMenuParameterId
    fun setHelpMenuParameterId(newValue: String) {
        _helpMenuParameterId.value = newValue
    }
}