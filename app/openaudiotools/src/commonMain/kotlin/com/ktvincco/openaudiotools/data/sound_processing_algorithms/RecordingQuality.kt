package com.ktvincco.openaudiotools.data.sound_processing_algorithms

import com.ktvincco.openaudiotools.Configuration


class RecordingQuality (
    private val state: String = "Good",
    private val text: String = "Recording"
) {

    fun getState(): String {
        return state
    }

    fun getText(): String {
        return text
    }
}


fun calculateRecordingIssue(loudness: Float): String {

    // Calculate current RecordingQuality
    // More serious issues process earlier

    // Too loud
    if (loudness == 1.0F) {
        return "TooLoud"
    }

    // All good
    return ""
}


fun findIssueByPatternInDataAndThrow(
    data: Array<String>, issue: String, pattern: String, patternCount: Int): Array<String> {

    // Check is data contain issue ar pattern
    if (data.count{ it == pattern } > patternCount || data.contains(issue) ) {

        // If data contain only pattern
        if (!data.contains(issue)) {

            // Cleanup pattern and throw issue to data
            data.map { if (it == pattern) "" else it }
            return data + issue
        }
    }
    return data
}


fun calculateRecordingQuality(recordingIssueArrayInput: Array<String>,
                              loudness: Float): Pair<Array<String>, RecordingQuality> {

    // Configuration
    val windowDurationSec = 4

    // Add current issue to data
    val recordingIssue = calculateRecordingIssue(loudness)
    val recordingIssueArray = recordingIssueArrayInput + recordingIssue

    // Process input
    val windowSize = (1F / Configuration.getProcessingSampleDurationSec()).toInt() * windowDurationSec
    val inputLength = recordingIssueArray.size

    // Check input
    if (inputLength < windowSize) { return Pair(recordingIssueArray, RecordingQuality()) }

    // Get data
    var data = recordingIssueArray.copyOfRange(inputLength - windowSize, inputLength)

    // Calculate recording quality
    // More serious issues process earlier
    var recordingQuality = RecordingQuality()

    // Too loud
    data = findIssueByPatternInDataAndThrow(
        data, "TooLoudIssue", "TooLoud", 8)
    if (data.contains("TooLoudIssue") ) {
        recordingQuality = RecordingQuality("Bad", "Too Loud !")
    }

    return Pair(data, recordingQuality)
}
