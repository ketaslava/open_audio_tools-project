package com.ktvincco.openvoiceanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.data.AndroidAudioPlayer
import com.ktvincco.openaudiotools.data.AndroidAudioRecorder
import com.ktvincco.openaudiotools.data.AndroidDatabase
import com.ktvincco.openaudiotools.data.AndroidEnvironmentConnector
import com.ktvincco.openaudiotools.data.AndroidLogger
import com.ktvincco.openaudiotools.data.AndroidPermissionController
import com.ktvincco.openaudiotools.data.AndroidSoundFile


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


@Composable
actual fun getScreenSizeInDp(): Pair<Dp, Dp> {
    val conf = LocalConfiguration.current
    return Pair(conf.screenWidthDp.dp, conf.screenHeightDp.dp)
}


@Composable
actual fun getScreenSizeInPx(): Pair<Int, Int> {
    val conf = LocalConfiguration.current
    val density = LocalDensity.current.density
    return Pair((conf.screenWidthDp * density).toInt(), (conf.screenHeightDp * density).toInt())
}