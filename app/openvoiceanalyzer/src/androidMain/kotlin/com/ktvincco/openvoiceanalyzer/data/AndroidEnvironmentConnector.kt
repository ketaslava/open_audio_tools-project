package com.ktvincco.openvoiceanalyzer.data

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.ktvincco.openvoiceanalyzer.MainActivity


class AndroidEnvironmentConnector (private val mainActivity: MainActivity): EnvironmentConnector {

    override fun openAppPermissionSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", mainActivity.packageName, null)
        intent.data = uri
        mainActivity.startActivity(intent)
    }

    override fun openWebLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mainActivity.startActivity(intent)
    }

    override fun restartTheApplication() {
        val intent = mainActivity.packageManager
            .getLaunchIntentForPackage(mainActivity.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        mainActivity.startActivity(intent)
        Runtime.getRuntime().exit(0)
    }

}