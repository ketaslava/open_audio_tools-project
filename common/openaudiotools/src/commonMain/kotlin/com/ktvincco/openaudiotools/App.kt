package com.ktvincco.openaudiotools

import androidx.compose.runtime.Composable
import com.ktvincco.openaudiotools.data.AudioPlayer
import com.ktvincco.openaudiotools.data.AudioRecorder
import com.ktvincco.openaudiotools.data.Database
import com.ktvincco.openaudiotools.data.EnvironmentConnector
import com.ktvincco.openaudiotools.data.Logger
import com.ktvincco.openaudiotools.data.PermissionController
import com.ktvincco.openaudiotools.data.SoundFile
import com.ktvincco.openaudiotools.domain.Main
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.presentation.UiEventHandler
import com.ktvincco.openaudiotools.ui.UserInterface
import org.jetbrains.compose.ui.tooling.preview.Preview


expect fun epochMillis(): Long


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