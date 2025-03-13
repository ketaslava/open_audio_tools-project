package com.ktvincco.openvoiceanalyzer.data


interface EnvironmentConnector {
    fun openAppPermissionSettings()
    fun openWebLink(url: String)
    fun restartTheApplication()
}