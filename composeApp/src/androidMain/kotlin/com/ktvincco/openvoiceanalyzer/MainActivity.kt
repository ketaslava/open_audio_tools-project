package com.ktvincco.openvoiceanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ktvincco.openvoiceanalyzer.data.AndroidAudioPlayer
import com.ktvincco.openvoiceanalyzer.data.AndroidAudioRecorder
import com.ktvincco.openvoiceanalyzer.data.AndroidDatabase
import com.ktvincco.openvoiceanalyzer.data.AndroidEnvironmentConnector
import com.ktvincco.openvoiceanalyzer.data.AndroidLogger
import com.ktvincco.openvoiceanalyzer.data.AndroidPermissionController
import com.ktvincco.openvoiceanalyzer.data.AndroidSoundFile


class MainActivity : ComponentActivity() {

    // Create platform components
    private val androidLogger = AndroidLogger()
    private val permissionController = AndroidPermissionController(this)
    private val audioRecorder = AndroidAudioRecorder()
    private val androidDatabase = AndroidDatabase(this)
    private val androidSoundFile = AndroidSoundFile()
    private val androidAudioPlayer = AndroidAudioPlayer()
    private val environmentConnector = AndroidEnvironmentConnector(this)


    // On Create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch common app
        setContent {
            App(androidLogger, permissionController, audioRecorder, androidDatabase,
                androidSoundFile, androidAudioPlayer, environmentConnector)
        }
    }


    // Permissions request callback
    @Deprecated("Deprecated in Java (Today don't have a Kotlin solution)")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        @Suppress("DEPRECATION")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Process in domainController
        permissionController.requestPermissionsResultCallback(
            requestCode, permissions, grantResults)
    }


    // Handle system "back" event
    @Deprecated("Deprecated in Java (Today don't have a Kotlin solution)",
        level = DeprecationLevel.HIDDEN)
    override fun onBackPressed() {
        // super.onBackPressed() <- close App
        // Do nothing
    }
}


actual fun epochMillis(): Long = System.currentTimeMillis()