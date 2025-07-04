package com.ktvincco.openaudiotools

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
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
    private val androidDatabase = AndroidDatabase(this, AppInfo.NAME)
    private val androidSoundFile = AndroidSoundFile()
    private val androidAudioPlayer = AndroidAudioPlayer()
    private val environmentConnector = AndroidEnvironmentConnector(this)


    // On Create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge UI layout
        enableEdgeToEdge()
        setDecorFitsSystemWindows(window, false)

        // Immersive mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+
            window.insetsController?.let { controller ->
                // Hide status bar and navigation
                controller.hide(WindowInsets.Type.systemBars())
                // Set return by swipe behaviour
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // Immersive mode for old android
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        // Launch common app
        setContent {
            App(androidLogger, permissionController, audioRecorder, androidDatabase,
                androidSoundFile, androidAudioPlayer, environmentConnector)
        }

        // Process system "back" event
        onBackPressedDispatcher.addCallback(this) {
            // Do nothing
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