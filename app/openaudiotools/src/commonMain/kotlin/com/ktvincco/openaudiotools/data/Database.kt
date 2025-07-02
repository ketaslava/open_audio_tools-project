package com.ktvincco.openaudiotools.data


interface Database {
    fun getSoundFileDirectoryPath(): String
    fun getAllSoundFilesInThePublicStorage(): List<String>
    fun moveFile(oldFilePath: String, newFilePath: String)
    fun deleteFile(filePath: String)
    fun saveString(key: String, string: String)
    fun loadString(key: String): String?
}