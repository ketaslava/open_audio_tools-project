package com.ktvincco.openvoiceanalyzer.data

interface PermissionController {
    fun requestPermissions(callback: (result: Boolean) -> Unit)
}