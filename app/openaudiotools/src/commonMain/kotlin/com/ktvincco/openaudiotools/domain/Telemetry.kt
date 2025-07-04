package com.ktvincco.openaudiotools.domain

import com.ktvincco.openaudiotools.AppInfo
import com.ktvincco.openaudiotools.Configuration
import com.ktvincco.openaudiotools.checkpointGate
import com.ktvincco.openaudiotools.data.Database
import com.ktvincco.openaudiotools.data.EnvironmentConnector
import com.ktvincco.openaudiotools.passCheckpointGate
import com.ktvincco.openaudiotools.timeGate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import java.security.SecureRandom


class Telemetry (private val database: Database,
                 private val  environmentConnector: EnvironmentConnector) {

    // State
    private var isEnabled = false


    fun generateInstallationId(): String {
        // Define a safe alphabet: digits + upper- and lower-case letters
        val alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        val rnd = SecureRandom()
        // Build a 16-character ID
        return buildString {
            repeat(16) {
                append(alphabet[rnd.nextInt(alphabet.length)])
            }
        }
    }


    // Get unique ID of current installation
    private fun getInstallationId(): String {
        var installationId = database.loadString("installationId")
        if (installationId == null || installationId == "") {
            installationId = generateInstallationId()
            database.saveString("installationId", installationId)
        }
        return installationId
    }


    // Initialization
    fun enable() {
        // Set state
        isEnabled = Configuration.getIsEnableTelemetry()
    }


    // Send any string payload
    fun sendStandardStatement(payload: Map<String, String>, result: (Boolean) -> Unit = {}) {

        // Check state
        if (!isEnabled) { return }

        // Add necessary info
        val necessaryInfo = mapOf (
            "appName" to AppInfo.NAME,
            "appVersion" to AppInfo.VERSION,
            "installationId" to getInstallationId(),
            "unixTime" to (System.currentTimeMillis() / 1000).toString(),
        )
        val combinedPayload = payload + necessaryInfo

        // Serialize payload
        val json = Json.encodeToString(JsonObject(
            combinedPayload.mapValues { JsonPrimitive(it.value) }))

        // Send payload
        environmentConnector.sendPOSTRequest(Configuration.getTelemetryApiEndpointUrl(),
            json) { success, response ->
            // Log response
            if (Configuration.getIsEnableTelemetryLogs()) {
                println("TELEMETRY POST RESPONSE: $success --> $response")
            }
            // Return result (check success by "success" in response)
            result(response != null && response.lowercase().contains("success"))
        }
    }


    // ==== Statements ====


    // Report about new installation
    fun newInstallationLaunchReport() {

        // One time gate (pass only once per installation)
        if (!checkpointGate("newInstallationLaunchReport", database)) { return }

        // Combine
        val statement = mapOf (
            "statementType" to "newInstallationLaunchReport",
        ) + environmentConnector.getUserInfo()

        // Send
        sendStandardStatement(statement) { result ->
            if (result) {
                passCheckpointGate("newInstallationLaunchReport", database)
            }
        }
    }


    // 6 Hours activity report
    fun sixHoursActivityReport() {

        // Six hours gate
        if (!timeGate("sixHoursActivityReport",
            21600, 1, database)) { return }

        // Combine
        val statement = mapOf (
            "statementType" to "sixHoursActivityReport",
        )

        // Send
        sendStandardStatement(statement)
    }


    // Information about function used in application
    fun usageReportByFunction(usedFunctionName: String) {

        // 32 per 12 hours gate
        if (!timeGate("usageReportByFunction",
            43200, 16, database)) { return }

        // Combine
        val statement = mapOf (
            "statementType" to "usageReportByFunction",
            "usedFunctionName" to usedFunctionName,
        )

        // Send
        sendStandardStatement(statement)
    }


    // Information about passed checkpoints in application
    fun usageReportByCheckpoint(checkpointName: String) {

        // One time gate
        if (!checkpointGate(checkpointName, database)) { return }

        // Combine
        val statement = mapOf (
            "statementType" to "usageReportByCheckpoint",
            "checkpointName" to checkpointName,
        )

        // Send
        sendStandardStatement(statement) { result ->
            if (result) {
                passCheckpointGate(checkpointName, database)
            }
        }
    }
}
