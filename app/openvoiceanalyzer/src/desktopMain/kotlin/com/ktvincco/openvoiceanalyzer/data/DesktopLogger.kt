package com.ktvincco.openvoiceanalyzer.data


class DesktopLogger: Logger {

    override fun log(logTag: String, message: String) {
        println("$logTag: $message")
    }

    override fun logW(logTag: String, message: String) {
        println("$logTag: $message")
    }

    override fun logE(logTag: String, message: String) {
        println("$logTag: $message")
    }

}