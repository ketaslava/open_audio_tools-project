package com.ktvincco.openaudiotools.data

interface PermissionController {
    fun requestPermissions(callback: (result: Boolean) -> Unit)
}