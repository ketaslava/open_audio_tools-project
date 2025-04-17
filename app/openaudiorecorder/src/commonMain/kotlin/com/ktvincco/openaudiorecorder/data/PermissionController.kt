package com.ktvincco.openaudiorecorder.data

interface PermissionController {
    fun requestPermissions(callback: (result: Boolean) -> Unit)
}