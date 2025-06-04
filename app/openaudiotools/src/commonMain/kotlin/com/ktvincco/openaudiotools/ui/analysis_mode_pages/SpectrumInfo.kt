package com.ktvincco.openaudiotools.ui.analysis_mode_pages

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.Settings
import com.ktvincco.openaudiotools.ui.charts.Graph
import com.ktvincco.openaudiotools.ui.charts.Spectrogram
import com.ktvincco.openaudiotools.ui.charts.Spectrum
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.presentation.UiEventHandler
import com.ktvincco.openaudiotools.ui.components.graphNameText


class SpectrumInfo (
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
                multiplyValue = 24F,
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

            // ======= Spectrum ======= //

            graphNameText(modelData, "Spectrum")

            Spectrum().Spectrum(
                inputData = spectrogramData,
                isUseLogScale = true,
                xLabelMax = 4096F,
                pointerPosition = pointerPosition,
                isUpdateFromLastData = recordingState,
                isDisableAutoResetByDataStructure = recordingState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // Bottom spacer

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}