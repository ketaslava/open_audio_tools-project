package com.ktvincco.openaudiotools.data

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.Locale


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


    override fun getYYYYMMDDHHMMSSString(): String {
        val currentMoment = Clock.System.now()
        val currentDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
        return " %04d %02d -- %02d %02d-%02d -- %02d ".format(
            Locale.ENGLISH,
            currentDateTime.year,
            currentDateTime.monthNumber,
            currentDateTime.dayOfMonth,
            currentDateTime.hour,
            currentDateTime.minute,
            currentDateTime.second
        )
    }


    override fun getDefaultLanguageCode(): String {
        return  activity.resources.configuration.locales.get(0).language.lowercase()
    }


    override fun forceGC() {
        // Not used cause no reason
    }
}