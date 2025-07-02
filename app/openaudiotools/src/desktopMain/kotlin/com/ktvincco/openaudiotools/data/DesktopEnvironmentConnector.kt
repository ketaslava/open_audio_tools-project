package com.ktvincco.openaudiotools.data

import java.awt.Desktop
import java.net.URI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class DesktopEnvironmentConnector: EnvironmentConnector {


    override fun openAppPermissionSettings() {}


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