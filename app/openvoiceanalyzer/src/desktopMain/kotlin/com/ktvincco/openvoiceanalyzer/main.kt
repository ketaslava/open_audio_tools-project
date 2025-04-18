package com.ktvincco.openvoiceanalyzer

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ktvincco.openaudiotools.data.DesktopAudioPlayer
import com.ktvincco.openaudiotools.data.DesktopAudioRecorder
import com.ktvincco.openaudiotools.data.DesktopDatabase
import com.ktvincco.openaudiotools.data.DesktopEnvironmentConnector
import com.ktvincco.openaudiotools.data.DesktopLogger
import com.ktvincco.openaudiotools.data.DesktopPermissionController
import com.ktvincco.openaudiotools.data.DesktopSoundFile
import openaudiotools.app.openvoiceanalyzer.generated.resources.Res
import openaudiotools.app.openvoiceanalyzer.generated.resources.icon
import org.jetbrains.compose.resources.painterResource
import java.awt.Toolkit


@Deprecated(message = "Migrate to the Compose resources library. See https:// www. jetbrains. com/ help/ kotlin-multiplatform-dev/ compose-images-resources. html If you need to load resources specificly from Java classpath, you should still use the new resource library and use a snippet from https:// github. com/ JetBrains/ compose-multiplatform-core/ pull/ 1457")
fun main() = application {

    Window(
        title = AppInfo.NAME,
        icon = painterResource(Res.drawable.icon),
        onCloseRequest = ::exitApplication,
    ) {
        // Create platform components
        val desktopLogger = DesktopLogger()
        val desktopPermissionController = DesktopPermissionController()
        val desktopAudioRecorder = DesktopAudioRecorder()
        val desktopDatabase = DesktopDatabase(AppInfo.NAME)
        val desktopSoundFile = DesktopSoundFile()
        val desktopAudioPlayer = DesktopAudioPlayer()
        val desktopEnvironmentConnector = DesktopEnvironmentConnector()

        // Launch common app
        App(desktopLogger, desktopPermissionController, desktopAudioRecorder,
            desktopDatabase, desktopSoundFile, desktopAudioPlayer, desktopEnvironmentConnector)
    }
}


@Composable
actual fun getScreenSizeInDp(): Pair<Dp, Dp> {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    // Assuming a standard density of 1.0f for Desktop (logical density may vary by use case)
    val density = 1.0f // Update this if you have specific density logic for your desktop app
    val widthDp = (screenSize.width / density).toInt()
    val heightDp = (screenSize.height / density).toInt()
    return Pair(widthDp.dp, heightDp.dp)
}


@Composable
actual fun getScreenSizeInPx(): Pair<Int, Int> {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    return Pair(screenSize.width, screenSize.height)
}
