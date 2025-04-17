package com.ktvincco.openaudiotools.presentation

class UiEventHandler {


    // Record Button
    private var recordButtonCallback: () -> Unit = {}
    fun assignRecordButtonCallback(callback: () -> Unit) {
        recordButtonCallback = callback }
    fun recordButtonClicked() { recordButtonCallback() }


    // Play Button
    private var playButtonCallback: () -> Unit = {}
    fun assignPlayButtonCallback(callback: () -> Unit) {
        playButtonCallback = callback }
    fun playButtonClicked() { playButtonCallback() }


    // Play File Button
    private var playFileButtonCallback: (fileName: String) -> Unit = {}
    fun assignPlayFileButtonCallback(callback: (fileName: String) -> Unit) {
        playFileButtonCallback = callback }
    fun playFileButtonClicked(fileName: String) { playFileButtonCallback(fileName) }


    // Reset Button
    private var resetButtonCallback: () -> Unit = {}
    fun assignResetButtonCallback(callback: () -> Unit) {
        resetButtonCallback = callback }
    fun resetButtonClicked() { resetButtonCallback() }


    // Rewind to start Button
    private var rewindToStartButtonCallback: () -> Unit = {}
    fun assignRewindToStartButtonCallback(callback: () -> Unit) {
        rewindToStartButtonCallback = callback }
    fun rewindToStartButtonClicked() { rewindToStartButtonCallback() }


    // Save Button
    private var saveButtonCallback: () -> Unit = {}
    fun assignSaveButtonCallback(callback: () -> Unit) {
        saveButtonCallback = callback }
    fun saveButtonClicked() { saveButtonCallback() }


    // Rename recording file
    private var renameRecordingFileCallback: (
        fileName: String, newNameInput: String) -> Unit = { _, _ -> }
    fun assignRenameRecordingFileCallback(
        callback: (fileName: String, newNameInput: String) -> Unit) {
        renameRecordingFileCallback = callback }
    fun renameRecordingFile(fileName: String, newNameInput: String) {
        renameRecordingFileCallback(fileName, newNameInput) }


    // Delete recording file
    private var deleteRecordingFileCallback: (fileName: String) -> Unit = {}
    fun assignDeleteRecordingFileCallback(callback: (fileName: String) -> Unit) {
        deleteRecordingFileCallback = callback }
    fun deleteRecordingFile(fileName: String) { deleteRecordingFileCallback(fileName) }


    // Load Recording Button
    private var loadRecordingButtonCallback: (fileName: String) -> Unit = {}
    fun assignLoadRecordingButtonCallback(callback: (fileName: String) -> Unit) {
        loadRecordingButtonCallback = callback }
    fun loadRecordingButtonClicked(fileName: String) { loadRecordingButtonCallback(fileName) }


    // Rewind
    private var rewind: (pointerPosition: Float) -> Unit = {}
    fun assignRewindCallback(callback: (pointerPosition: Float) -> Unit) { rewind = callback }
    fun rewindCallback(pointerPosition: Float) { rewind.invoke(pointerPosition) }


    // Open App Permission Settings Button
    private var openAppPermissionSettingsButtonCallback: () -> Unit = {}
    fun assignOpenAppPermissionSettingsButtonCallback(callback: () -> Unit) {
        openAppPermissionSettingsButtonCallback = callback }
    fun openAppPermissionSettingsButtonClicked() { openAppPermissionSettingsButtonCallback() }


    // Restart App Button
    private var restartAppButtonCallback: () -> Unit = {}
    fun assignRestartAppButtonCallback(callback: () -> Unit) {
        restartAppButtonCallback = callback }
    fun restartAppButtonClicked() { restartAppButtonCallback() }


    // Open WEB link button
    private var openWebLinkButtonCallback: (url: String) -> Unit = {}
    fun assignOpenWebLinkButtonCallback(callback: (url: String) -> Unit) {
        openWebLinkButtonCallback = callback }
    fun openWebLinkButtonCallbackClicked(url: String) { openWebLinkButtonCallback(url) }
}