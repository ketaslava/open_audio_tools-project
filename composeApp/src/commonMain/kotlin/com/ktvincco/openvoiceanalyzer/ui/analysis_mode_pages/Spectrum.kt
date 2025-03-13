package com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ktvincco.openvoiceanalyzer.Settings
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler
import com.ktvincco.openvoiceanalyzer.ui.Graph
import com.ktvincco.openvoiceanalyzer.ui.Spectrogram
import com.ktvincco.openvoiceanalyzer.ui.graphNameText


class Spectrum (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    // Loudness
    // Spectrogram
    // Energy Spectrogram

    @Composable
    fun content(): @Composable ColumnScope.() -> Unit {
        return {

            // Get data

            val pointerPosition = modelData.pointerPosition.collectAsState().value
            val dataDurationSec = modelData.dataDurationSec.collectAsState().value
            val recordingState = modelData.recordingState.collectAsState().value

            // ======= Loudness ======= //

            val loudnessData = modelData.getGraphData("Loudness")

            graphNameText(modelData, "Loudness")

            Graph().draw(
                data = loudnessData,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Spectrogram ======= //

            val spectrogramData =
                modelData.getSpectrogramData("SpectrogramInHz")

            graphNameText(modelData, "SpectrogramInHz")

            Spectrogram().Spectrogram(
                data = spectrogramData,
                multiplyValue = 16F,
                xLabelMin = 0F,
                xLabelMax = dataDurationSec,
                yLabelMin = 0F,
                yLabelMax = 4096F,
                horizontalLinesCount = 8,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= Energy Spectrogram ======= //

            val formantSpectrogramData =
                modelData.getSpectrogramData("EnergySpectrogramInHz")

            graphNameText(modelData, "EnergySpectrogramInHz")

            Spectrogram().Spectrogram(
                data = formantSpectrogramData,
                xLabelMin = 0F,
                xLabelMax = dataDurationSec,
                yLabelMin = 0F,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // Bottom spacer

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}