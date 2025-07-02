package com.ktvincco.openaudiotools.domain

import com.ktvincco.openaudiotools.AppInfo
import com.ktvincco.openaudiotools.Configuration
import com.ktvincco.openaudiotools.data.AudioPlayer
import com.ktvincco.openaudiotools.data.AudioRecorder
import com.ktvincco.openaudiotools.data.Database
import com.ktvincco.openaudiotools.data.EnvironmentConnector
import com.ktvincco.openaudiotools.data.Logger
import com.ktvincco.openaudiotools.data.PermissionController
import com.ktvincco.openaudiotools.data.SoundFile
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.presentation.UiEventHandler

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
        permissionController, audioRecorder, database, environmentConnector, soundFile, audioPlayer)


    fun setup() {

        // Info
        modelData.setAppInfo("Name", AppInfo.NAME)
        modelData.setAppInfo("Version", AppInfo.VERSION)

        // Language
        // null or "" -> follow system
        // lang code -> specific lang
        // non lang code (any string) -> original
        var languageCode = database.loadString("languageCode")
        if ((languageCode == null) or (languageCode == "")) {
            languageCode = environmentConnector.getDefaultLanguageCode()
        }
        modelData.setLanguageCode(languageCode?: "original")

        // Language selection callback
        uiEventHandler.assignLanguageSelectedCallback { newLanguageCode ->
            database.saveString("languageCode", newLanguageCode)
            modelData.setLanguageCode(newLanguageCode)
        }

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
                !Configuration.getIsAlwaysShowFirstStartScreen() &&
                database.loadString("AcceptedUserAgreementVersion") ==
                    Configuration.getUserAgreementVersion().toString()

        if (isComplete) {
            modelData.openAllInfoPage()
            // DEV
            modelData.openSettingsPage()
        } else {
            modelData.openFirstStartScreen {
                database.saveString("IsFirstStartComplete", "Yes")
                database.saveString("AcceptedUserAgreementVersion",
                    Configuration.getUserAgreementVersion().toString())
                modelData.openDashboardPage()
            }
        }
    }

}
