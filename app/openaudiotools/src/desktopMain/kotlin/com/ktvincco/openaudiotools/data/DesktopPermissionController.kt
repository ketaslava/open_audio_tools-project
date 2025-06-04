package com.ktvincco.openaudiotools.data


class DesktopPermissionController: PermissionController {

    override fun requestPermissions(callback: (result: Boolean) -> Unit) {

        // Return true cause we always have all permissions
        callback(true)
    }

}