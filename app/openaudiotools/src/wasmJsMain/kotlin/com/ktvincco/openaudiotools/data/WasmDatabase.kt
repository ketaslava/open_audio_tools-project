package com.ktvincco.openaudiotools.data


class WasmDatabase (private val appName: String): Database {

    override fun getSoundFileDirectoryPath(): String {
        TODO("Not yet implemented")
    }

    override fun getAllSoundFilesInThePublicStorage(): List<String> {
        TODO("Not yet implemented")
    }

    override fun moveFile(oldFilePath: String, newFilePath: String) {
        TODO("Not yet implemented")
    }

    override fun deleteFile(filePath: String) {
        TODO("Not yet implemented")
    }

    override fun getYYYYMMDDHHMMSSString(): String {
        TODO("Not yet implemented")
    }

    override fun saveString(key: String, string: String) {
        TODO("Not yet implemented")
    }

    override fun loadString(key: String): String {
        TODO("Not yet implemented")
    }

    override fun forceGC() {
        TODO("Not yet implemented")
    }

}

