package com.ktvincco.openaudiorecorder.data

import java.awt.Desktop
import java.net.URI


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
}