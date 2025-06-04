package com.ktvincco.openaudiotools.data

import java.io.File
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class DesktopDatabase (private val appName: String): Database {

    companion object {
        const val LOG_TAG = "DesktopDatabase"
    }


    // Program folder path (in the public storage)
    private fun getSoundFileDirectory(): File {
        val dir = File(System.getProperty("user.home"), appName.replace(" ", ""))
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }


    override fun getSoundFileDirectoryPath(): String {
        return getSoundFileDirectory().absolutePath
    }


    override fun getAllSoundFilesInThePublicStorage(): List<String> {
        val directory = getSoundFileDirectory()
        return if (directory.exists() && directory.isDirectory) {
            directory.listFiles { file -> file.isFile }?.map { it.name } ?: emptyList()
        } else {
            emptyList()
        }
    }


    override fun moveFile(oldFilePath: String, newFilePath: String) {
        try {
            val oldFile = File(oldFilePath)
            val newFile = File(newFilePath)

            if (!oldFile.exists()) {
                throw IOException("File not found at path: $oldFilePath")
            }

            val newParentDir = newFile.parentFile
            if (newParentDir != null && !newParentDir.exists()) {
                newParentDir.mkdirs()
            }

            if (!oldFile.renameTo(newFile)) {
                oldFile.copyTo(newFile, overwrite = true)
                oldFile.delete()
            }
        } catch (e: Exception) {
            throw IOException(
                "Failed to move file from $oldFilePath to $newFilePath: ${e.message}", e)
        }
    }


    override fun deleteFile(filePath: String) {
        val file = File(filePath)
        if (file.exists()) {
            if (file.delete()) {
                println("$LOG_TAG: File $filePath deleted successfully.")
            } else {
                println("$LOG_TAG: Failed to delete file $filePath.")
            }
        } else {
            println("$LOG_TAG: File $filePath does not exist.")
        }
    }


    override fun getYYYYMMDDHHMMSSString(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(" yyyy MM >> dd HH:mm << ss ", Locale.ENGLISH)
        return currentDateTime.format(formatter)
    }


    private val preferences = java.util.prefs.Preferences.
        userNodeForPackage(DesktopDatabase::class.java)


    override fun saveString(key: String, string: String) {
        preferences.put(key, string)
        println("By key [$key] saved string: $string")
    }


    override fun loadString(key: String): String {
        val string = preferences.get(key, "")
        println("By key [$key] loaded string: $string")
        return string
    }

    override fun forceGC() {
        System.gc()
    }
}

