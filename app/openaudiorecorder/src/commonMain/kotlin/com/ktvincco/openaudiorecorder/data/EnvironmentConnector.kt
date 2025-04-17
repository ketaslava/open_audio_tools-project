package com.ktvincco.openaudiorecorder.data


interface EnvironmentConnector {
    fun openAppPermissionSettings()
    fun openWebLink(url: String)
    fun restartTheApplication()
}