package com.ktvincco.openaudiotools.data

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings


class AndroidEnvironmentConnector (private val activity: Activity): EnvironmentConnector {

    override fun openAppPermissionSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivity(intent)
    }

    override fun openWebLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
    }

    override fun restartTheApplication() {
        val intent = activity.packageManager
            .getLaunchIntentForPackage(activity.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
        Runtime.getRuntime().exit(0)
    }

}