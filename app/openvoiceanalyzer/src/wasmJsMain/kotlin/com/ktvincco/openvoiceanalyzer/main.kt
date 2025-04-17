package com.ktvincco.openvoiceanalyzer

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.ktvincco.openvoiceanalyzer.data.WasmAudioPlayer
import com.ktvincco.openvoiceanalyzer.data.WasmAudioRecorder
import com.ktvincco.openvoiceanalyzer.data.WasmDatabase
import com.ktvincco.openvoiceanalyzer.data.WasmEnvironmentConnector
import com.ktvincco.openvoiceanalyzer.data.WasmLogger
import com.ktvincco.openvoiceanalyzer.data.WasmPermissionController
import com.ktvincco.openvoiceanalyzer.data.WasmSoundFile
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