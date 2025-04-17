package com.ktvincco.openvoiceanalyzer

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.ktvincco.openaudiotools.data.AudioPlayer
import com.ktvincco.openaudiotools.data.AudioRecorder
import com.ktvincco.openaudiotools.data.Database
import com.ktvincco.openaudiotools.data.EnvironmentConnector
import com.ktvincco.openaudiotools.data.Logger
import com.ktvincco.openaudiotools.data.PermissionController
import com.ktvincco.openaudiotools.data.SoundFile
import com.ktvincco.openvoiceanalyzer.domain.Main
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler
import com.ktvincco.openvoiceanalyzer.ui.UserInterface
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(
    logger: Logger,
    permissionController: PermissionController,
    audioRecorder: AudioRecorder,
    database: Database,
    soundFile: SoundFile,
    audioPlayer: AudioPlayer,
    environmentConnector: EnvironmentConnector
) {

    // Create components
    val modelData = ModelData()
    val uiEventHandler = UiEventHandler()
    val domainMain = Main(modelData, uiEventHandler, logger, permissionController,
        audioRecorder, database, soundFile, audioPlayer, environmentConnector)
    val userInterface = UserInterface(modelData, uiEventHandler)

    // Run actions
    domainMain.setup()
    userInterface.draw()
}


expect fun epochMillis(): Long


@Composable
expect fun getScreenSizeInDp(): Pair<Dp, Dp>
@Composable
expect fun getScreenSizeInPx(): Pair<Int, Int>