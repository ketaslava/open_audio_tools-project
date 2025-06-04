package com.ktvincco.openaudiotools.data

interface Logger {
    fun log(logTag: String, message: String)
    fun logW(logTag: String, message: String)
    fun logE(logTag: String, message: String)
}