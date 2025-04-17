package com.ktvincco.openaudiorecorder

import androidx.compose.runtime.Composable
import com.ktvincco.openaudiorecorder.data.AudioPlayer
import com.ktvincco.openaudiorecorder.data.AudioRecorder
import com.ktvincco.openaudiorecorder.data.Database
import com.ktvincco.openaudiorecorder.data.EnvironmentConnector
import com.ktvincco.openaudiorecorder.data.Logger
import com.ktvincco.openaudiorecorder.data.PermissionController
import com.ktvincco.openaudiorecorder.data.SoundFile
import com.ktvincco.openaudiorecorder.domain.Main
import com.ktvincco.openaudiorecorder.presentation.ModelData
import com.ktvincco.openaudiorecorder.presentation.UiEventHandler
import com.ktvincco.openaudiorecorder.ui.UserInterface
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