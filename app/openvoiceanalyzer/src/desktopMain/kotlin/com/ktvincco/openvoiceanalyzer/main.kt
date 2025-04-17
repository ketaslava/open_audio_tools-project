package com.ktvincco.openvoiceanalyzer

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ktvincco.openvoiceanalyzer.data.DesktopAudioPlayer
import com.ktvincco.openvoiceanalyzer.data.DesktopAudioRecorder
import com.ktvincco.openvoiceanalyzer.data.DesktopDatabase
import com.ktvincco.openvoiceanalyzer.data.DesktopEnvironmentConnector
import com.ktvincco.openvoiceanalyzer.data.DesktopLogger
import com.ktvincco.openvoiceanalyzer.data.DesktopPermissionController
import com.ktvincco.openvoiceanalyzer.data.DesktopSoundFile
import openaudiotools.app.openvoiceanalyzer.generated.resources.Res
import openaudiotools.app.openvoiceanalyzer.generated.resources.icon
import org.jetbrains.compose.resources.painterResource


@Deprecated(message = "Migrate to the Compose resources library. See https:// www. jetbrains. com/ help/ kotlin-multiplatform-dev/ compose-images-resources. html If you need to load resources specificly from Java classpath, you should still use the new resource library and use a snippet from https:// github. com/ JetBrains/ compose-multiplatform-core/ pull/ 1457")
fun main() = application {

    Window(
        title = "Open Voice Analyzer",
        icon = painterResource(Res.drawable.icon),
        onCloseRequest = ::exitApplication,
    ) {
        // Create platform components
        val desktopLogger = DesktopLogger()
        val desktopPermissionController = DesktopPermissionController()
        val desktopAudioRecorder = DesktopAudioRecorder()
        val desktopDatabase = DesktopDatabase()
        val desktopSoundFile = DesktopSoundFile()
        val desktopAudioPlayer = DesktopAudioPlayer()
        val desktopEnvironmentConnector = DesktopEnvironmentConnector()

        // Launch common app
        App(desktopLogger, desktopPermissionController, desktopAudioRecorder,
            desktopDatabase, desktopSoundFile, desktopAudioPlayer, desktopEnvironmentConnector)
    }
}