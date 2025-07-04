package com.ktvincco.openaudiotools.data

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.net.HttpURLConnection
import java.net.URL
import java.util.Locale
import androidx.core.net.toUri
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


class AndroidEnvironmentConnector (private val activity: Activity): EnvironmentConnector {


    override fun openAppPermissionSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivity(intent)
    }


    override fun sendPOSTRequest(
        url: String, payload: String,
        onResult: (success: Boolean, response: String?) -> Unit
    ) {
        // Start async task to request
        CoroutineScope(Dispatchers.IO).launch {

            // Request for result
            val result = withContext(Dispatchers.IO) {
                try {

                    // Configure and send request thru connection
                    val connection = (URL(url).openConnection() as HttpURLConnection).apply {
                        requestMethod = "POST"
                        doOutput = true
                        setRequestProperty("Content-Type", "text/plain; charset=utf-8")
                        connectTimeout = 10_000
                        readTimeout = 10_000
                    }
                    connection.outputStream.use { it.write(payload.toByteArray()) }

                    // Process response and get data
                    val stream = if (connection.responseCode in 200..299)
                        connection.inputStream else connection.errorStream
                    val response = stream.bufferedReader().use { it.readText() }

                    // Disconnect
                    connection.disconnect()

                    // Return result
                    Pair(true, response)

                } catch (e: Exception) {

                    // Return result
                    Pair(false, e.message)
                }
            }

            // Call callback
            onResult(result.first, result.second)
        }
    }


    override fun getUserInfo(): Map<String, String> {
        val info = mutableMapOf<String, String>()

        // Device type
        info["deviceType"] = "Android"

        try {
            // Software info
            info["manufacturer"] = Build.MANUFACTURER
            info["model"] = Build.MODEL
            info["androidVersion"] = Build.VERSION.RELEASE
            info["apiLevel"] = Build.VERSION.SDK_INT.toString()

            try {
                // Memory and CPU info
                val activityManager = activity.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
                val memInfo = ActivityManager.MemoryInfo()

                activityManager?.getMemoryInfo(memInfo)

                // CPU cores
                val cores = Runtime.getRuntime().availableProcessors()

                info["totalRamMb"] = (memInfo.totalMem / (1024 * 1024)).toString()
                info["availableRamMb"] = (memInfo.availMem / (1024 * 1024)).toString()
                info["cpuCores"] = cores.toString()

            } catch (e: Exception) {
                info["errorInSystemConfigurationCollection"] = e.message ?: "unknown error"
            }

            // Locale & Timezone
            val locale = Locale.getDefault()
            info["language"] = locale.language
            info["country"] = locale.country
            info["timeZone"] = TimeZone.currentSystemDefault().id

        } catch (e: Exception) {
            info["error"] = e.message ?: "unknown error"
        }

        return info
    }


    override fun openWebLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
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


    @OptIn(ExperimentalTime::class)
    override fun getYYYYMMDDHHMMSSString(): String {
        val currentMoment = Clock.System.now()
        val currentDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
        return " %04d %02d -- %02d %02d-%02d -- %02d ".format(
            Locale.ENGLISH,
            currentDateTime.year,
            currentDateTime.month.number,
            currentDateTime.day,
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