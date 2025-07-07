package com.ktvincco.openaudiotools.data

import java.awt.Desktop
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone


class DesktopEnvironmentConnector: EnvironmentConnector {


    override fun openAppPermissionSettings() {}


    override fun sendPOSTRequest(
        url: String,
        payload: String,
        onResult: (success: Boolean, responseBody: String?) -> Unit
    ) {
        try {
            // Create a shared HttpClient (you could reuse this across calls)
            val client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build()

            // Build the POST request with plain-text body
            val request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .header("Content-Type", "text/plain; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build()

            // Send asynchronously
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenComplete { response, error ->
                    if (error != null) {
                        // Network / I/O exception
                        onResult(false, error.message)
                    } else {
                        // Got a response—report success if 2xx, otherwise false
                        val code = response.statusCode()
                        onResult(
                            code in 200..299,
                            response.body()
                        )
                    }
                }
        } catch (e: Exception) {
            onResult(false, e.message)
        }
    }


    override fun getUserInfo(): Map<String, String> {
        val info = mutableMapOf<String, String>()

        // Device type
        info["deviceType"] = "Desktop"

        try {
            // OS info
            info["osName"] = System.getProperty("os.name") ?: "unknown"
            info["osVersion"] = System.getProperty("os.version") ?: "unknown"
            info["osArch"] = System.getProperty("os.arch") ?: "unknown"

            // Java info
            info["javaVersion"] = System.getProperty("java.version") ?: "unknown"
            info["jvmName"] = System.getProperty("java.vm.name") ?: "unknown"
            info["jvmVendor"] = System.getProperty("java.vm.vendor") ?: "unknown"

            try {
                // CPU cores
                info["cpuCores"] = Runtime.getRuntime().availableProcessors().toString()

                // Memory info
                val runtime = Runtime.getRuntime()
                info["totalMemoryMb"] = (runtime.totalMemory() / (1024 * 1024)).toString()
                info["freeMemoryMb"] = (runtime.freeMemory() / (1024 * 1024)).toString()
                info["maxMemoryMb"] = (runtime.maxMemory() / (1024 * 1024)).toString()

                // Disk space
                val root = java.io.File(System.getProperty("user.home"))
                info["diskTotalGb"] = (root.totalSpace / (1024 * 1024 * 1024)).toString()
                info["diskFreeGb"] = (root.freeSpace / (1024 * 1024 * 1024)).toString()

            } catch (e: Exception) {
                info["errorInSystemConfigurationCollection"] = e.message ?: "unknown error"
            }

            // Locale & Timezone
            val locale = Locale.getDefault()
            info["language"] = locale.language
            info["country"] = locale.country
            info["timeZone"] = TimeZone.getDefault().id

        } catch (e: Exception) {
            info["error"] = e.message ?: "unknown error"
        }

        return info
    }


    override fun openWebLink(url: String) {
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(URI(url))
            } else {
                throw UnsupportedOperationException("Link opening is not supported")
            }
        } else {
            throw UnsupportedOperationException("Desktop API not supported")
        }
    }

    override fun restartTheApplication() {}


    override fun getYYYYMMDDHHMMSSString(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(" yyyy MM >> dd HH:mm << ss ", Locale.ENGLISH)
        return currentDateTime.format(formatter)
    }


    override fun getDefaultLanguageCode(): String {
        return Locale.getDefault().language.lowercase()
    }


    override fun forceGC() {
        System.gc()
    }
}