package com.ktvincco.openaudiotools.data


interface EnvironmentConnector {
    fun openAppPermissionSettings()
    fun openWebLink(url: String)
    fun restartTheApplication()
}