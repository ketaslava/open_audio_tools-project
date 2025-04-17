package com.ktvincco.openaudiorecorder.domain

import com.ktvincco.openaudiorecorder.Settings
import com.ktvincco.openaudiorecorder.data.AudioPlayer
import com.ktvincco.openaudiorecorder.data.AudioRecorder
import com.ktvincco.openaudiorecorder.data.Database
import com.ktvincco.openaudiorecorder.data.EnvironmentConnector
import com.ktvincco.openaudiorecorder.data.Logger
import com.ktvincco.openaudiorecorder.data.PermissionController
import com.ktvincco.openaudiorecorder.data.SoundFile
import com.ktvincco.openaudiorecorder.presentation.ModelData
import com.ktvincco.openaudiorecorder.presentation.UiEventHandler

class Main (private val modelData: ModelData,
            private val uiEventHandler: UiEventHandler,
            private val logger: Logger,
            private val permissionController: PermissionController,
            private val audioRecorder: AudioRecorder,
            private val database: Database,
            private val soundFile: SoundFile,
            private val audioPlayer: AudioPlayer,
            private val environmentConnector: EnvironmentConnector
){

    companion object {
        const val LOG_TAG = "Main"
    }


    // Create components
    private val recorder = Recorder(modelData, uiEventHandler, logger,
        permissionController, audioRecorder, database, soundFile, audioPlayer)


    fun setup() {

        // Callbacks
        uiEventHandler.assignOpenAppPermissionSettingsButtonCallback {
            environmentConnector.openAppPermissionSettings()
        }
        uiEventHandler.assignRestartAppButtonCallback {
            environmentConnector.restartTheApplication()
        }
        uiEventHandler.assignOpenWebLinkButtonCallback { url ->
            environmentConnector.openWebLink(url)
        }

        // Next
        requestPermissions()
    }


    private fun requestPermissions() {
        permissionController.requestPermissions { result ->

            // Process permissions request result
            if (result) {
                logger.log(LOG_TAG, "Permissions granted")
                setup2()
            } else {
                logger.logW(LOG_TAG, "WARNING access denied")
                modelData.openAccessDeniedScreen()
                modelData.setIsShowUi(true)
            }
        }
    }


    private fun setup2() {

        // Setup components
        recorder.setup()

        // Show UI
        modelData.setIsShowUi(true)

        // Open dashboard or FirstStartScreen
        val isComplete = database.loadString("IsFirstStartComplete") == "Yes" &&
                !Settings.getIsAlwaysShowFirstStartScreen() &&
                database.loadString("AcceptedUserAgreementVersion") ==
                    Settings.getUserAgreementVersion().toString()

        if (isComplete) {
            modelData.openAllInfoPage()
            // DEV
            // modelData.openSpectrumInfoPage()
        } else {
            modelData.openFirstStartScreen {
                database.saveString("IsFirstStartComplete", "Yes")
                database.saveString("AcceptedUserAgreementVersion",
                    Settings.getUserAgreementVersion().toString())
                modelData.openDashboardPage()
            }
        }
    }

}
