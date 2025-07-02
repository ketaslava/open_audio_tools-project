package com.ktvincco.openaudiotools.data

import java.io.File


class DesktopLogger (private val appName: String): Logger {

    override fun log(logTag: String, message: String) {
        println("$logTag: $message")
    }

    override fun logW(logTag: String, message: String) {
        println("$logTag: $message")
    }

    override fun logE(logTag: String, message: String) {
        println("$logTag: $message")
    }

    override fun logUniqueString(string: String, fileName: String) {

        // Escape actual line breaks to make single-line representation
        val sanitizedString = string.replace("\n", "\\n")

        // Open directory
        val dir = File(System.getProperty("user.home"), appName.replace(" ", ""))
        if (!dir.exists()) {
            dir.mkdirs()
        }

        // Open file
        val file = File(dir, "/$fileName.txt")
        if (!file.exists()) {
            file.createNewFile()
        }

        // Read all lines
        val existing = file.readLines()

        // If not already present, append it
        if (!existing.contains(sanitizedString)) {
            file.appendText(sanitizedString + "\n")
        }
    }
}