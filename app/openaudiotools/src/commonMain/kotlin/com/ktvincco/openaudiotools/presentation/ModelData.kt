package com.ktvincco.openaudiotools.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import com.ktvincco.openaudiotools.data.sound_processing_algorithms.RecordingQuality
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ModelData {

    // Ui switch
    private val _isShowUi = MutableStateFlow(false)
    val isShowUi: StateFlow<Boolean> = _isShowUi
    fun setIsShowUi(newValue: Boolean) {
        _isShowUi.value = newValue
    }


    // App Info
    private val _appInfo = mutableStateMapOf<String, String>()
    fun setAppInfo(key: String, value: String) {
        _appInfo[key] = value
    }
    @Composable
    fun getAppInfo(key: String): String {
        return _appInfo[key] ?: ""
    }


    // Language code
    private val _languageCode = MutableStateFlow("ru")
    val languageCode: StateFlow<String> = _languageCode
    fun setLanguageCode(newValue: String) {
        _languageCode.value = newValue
    }


    // Loading screen overlay
    private val _isShowLoadingScreenOverlay = MutableStateFlow(false)
    val isShowLoadingScreenOverlay: StateFlow<Boolean> = _isShowLoadingScreenOverlay
    fun setIsShowLoadingScreenOverlay(newValue: Boolean) {
        _isShowLoadingScreenOverlay.value = newValue
    }


    // First start screen
    private var firstStartScreenClosedCallback: () -> Unit = {}
    fun firstStartScreenClosed() {
        firstStartScreenClosedCallback()
    }


    // Current page
    val _currentPage = MutableStateFlow("Dashboard")
    val currentPage: StateFlow<String> = _currentPage
    fun openFirstStartScreen(callback: () -> Unit) {
        firstStartScreenClosedCallback = callback
        _currentPage.value = "FirstStartScreen"
    }


    // Default pages
    fun openDashboardPage() { _currentPage.value = "Dashboard" }
    fun openRecordingsPage() { _currentPage.value = "Recordings" }
    fun openAccessDeniedScreen() { _currentPage.value = "AccessDeniedScreen" }


    // Legal Info Screen
    private val _legalInfoScreenState = MutableStateFlow(false)
    val legalInfoScreenState: StateFlow<Boolean> = _legalInfoScreenState
    fun setLegalInfoScreenState(newValue: Boolean) {
        _legalInfoScreenState.value = newValue
    }


    // Main menu
    private val _mainMenuState = MutableStateFlow(false)
    val mainMenuState: StateFlow<Boolean> = _mainMenuState
    fun switchMainMenuState() {
        _mainMenuState.value = !_mainMenuState.value
    }

    fun setMainMenuState(newValue: Boolean) {
        _mainMenuState.value = newValue
    }


    // Recording State
    private val _recordingState = MutableStateFlow(false)
    val recordingState: StateFlow<Boolean> = _recordingState
    fun setRecordingState(newValue: Boolean) {
        _recordingState.value = newValue
    }


    // Playback State
    private val _playbackState = MutableStateFlow(false)
    val playbackState: StateFlow<Boolean> = _playbackState
    fun setPlaybackState(newValue: Boolean) {
        _playbackState.value = newValue
    }


    // Recording quality
    private val _recordingQuality = MutableStateFlow(RecordingQuality())
    val recordingQuality: StateFlow<RecordingQuality> = _recordingQuality
    fun setRecordingQuality(recordingQuality: RecordingQuality) {
        _recordingQuality.value = recordingQuality
    }


    // Pointer position
    private val _pointerPosition = MutableStateFlow(0F)
    val pointerPosition: StateFlow<Float> = _pointerPosition
    fun setPointerPosition(newValue: Float) {
        _pointerPosition.value = newValue
    }


    // Data duration sec
    private val _dataDurationSec = MutableStateFlow(1F)
    val dataDurationSec: StateFlow<Float> = _dataDurationSec
    fun setDataDurationSec(newValue: Float) {
        _dataDurationSec.value = newValue
    }


    // Recordings list
    private val _recordingFileList = MutableStateFlow<List<String>>(listOf())
    val recordingFileList: StateFlow<List<String>> = _recordingFileList
    fun setRecordingFileList(newValue: List<String>) {
        _recordingFileList.value = newValue
    }


    // Popup
    private val _isPopupOpened = MutableStateFlow(false)
    val isPopupOpened: StateFlow<Boolean> = _isPopupOpened
    private val _popupHeadline = MutableStateFlow("Info")
    val popupHeadline: StateFlow<String> = _popupHeadline
    private val _popupText = MutableStateFlow("Text")
    val popupText: StateFlow<String> = _popupText
    private var popupCallback: (exitButtonType: String) -> Unit = {}
    fun openPopup(headline: String, text: String, callback: (exitButtonType: String) -> Unit) {
        _isPopupOpened.value = true
        _popupHeadline.value = headline
        _popupText.value = text
        popupCallback = callback
    }

    fun closePopup(exitButtonType: String) {
        _isPopupOpened.value = false
        popupCallback.invoke(exitButtonType)
    }


    // Popup With Text Input
    private val _isPopupWithTextInputOpened = MutableStateFlow(false)
    val isPopupWithTextInputOpened: StateFlow<Boolean> = _isPopupWithTextInputOpened
    private var popupWithTextInputCallback: (
        exitButtonType: String, inputText: String
    ) -> Unit = { _, _ -> }

    fun openPopupWithTextInput(
        headline: String, callback: (
            exitButtonType: String, inputText: String
        ) -> Unit
    ) {
        _isPopupWithTextInputOpened.value = true
        _popupHeadline.value = headline
        popupWithTextInputCallback = callback
    }

    fun closePopupWithTextInput(exitButtonType: String, inputText: String) {
        _isPopupWithTextInputOpened.value = false
        popupWithTextInputCallback.invoke(exitButtonType, inputText)
    }

    // Default pages
    fun openVoiceChangeGuidelinesPage() { _currentPage.value = "VoiceChangeGuidelines" }

    // Mode pages
    fun openAllInfoPage() { _currentPage.value = "AllInfo" }
    fun openSpectrumInfoPage() { _currentPage.value = "SpectrumInfo" }
    fun openReadingPage() { _currentPage.value = "Reading" }
    fun openSpeakerVoicePage() { _currentPage.value = "SpeakerVoice" }
    fun openSingingPage() { _currentPage.value = "Singing" }
    fun openPitchAndResonancePage() { _currentPage.value = "PitchAndResonance" }
    fun openVoiceSmoothnessPage() { _currentPage.value = "VoiceSmoothness" }
    fun openFemaleVoicePage() { _currentPage.value = "FemaleVoice" }
    fun openFemaleVoiceResonancePage() { _currentPage.value = "FemaleVoiceResonance" }
    fun openMaleVoicePage() { _currentPage.value = "MaleVoice" }
    fun openMaleVoiceResonancePage() { _currentPage.value = "MaleVoiceResonance" }


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