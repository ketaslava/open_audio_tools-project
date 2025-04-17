package com.ktvincco.openaudiorecorder

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.ktvincco.openaudiorecorder.data.WasmAudioPlayer
import com.ktvincco.openaudiorecorder.data.WasmAudioRecorder
import com.ktvincco.openaudiorecorder.data.WasmDatabase
import com.ktvincco.openaudiorecorder.data.WasmEnvironmentConnector
import com.ktvincco.openaudiorecorder.data.WasmLogger
import com.ktvincco.openaudiorecorder.data.WasmPermissionController
import com.ktvincco.openaudiorecorder.data.WasmSoundFile
import kotlinx.browser.document


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {

        // Create platform components
        val wasmLogger = WasmLogger()
        val wasmPermissionController = WasmPermissionController()
        val wasmAudioRecorder = WasmAudioRecorder()
        val wasmDatabase = WasmDatabase()
        val wasmSoundFile = WasmSoundFile()
        val wasmAudioPlayer = WasmAudioPlayer()
        val wasmEnvironmentConnector = WasmEnvironmentConnector()


        // Launch common app
        App(wasmLogger, wasmPermissionController, wasmAudioRecorder,
            wasmDatabase, wasmSoundFile, wasmAudioPlayer, wasmEnvironmentConnector)
    }
}


actual fun epochMillis(): Long {
    TODO("Not yet implemented")
}