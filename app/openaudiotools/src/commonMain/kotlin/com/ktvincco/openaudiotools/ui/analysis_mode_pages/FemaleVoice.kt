package com.ktvincco.openaudiotools.ui.analysis_mode_pages

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.Configuration
import com.ktvincco.openaudiotools.ui.charts.Graph
import com.ktvincco.openaudiotools.ui.charts.GraphZone
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.ui.components.graphNameText
import com.ktvincco.openaudiotools.ui.components.miniDisplayBox


class FemaleVoice (
    private val modelData: ModelData
) {

    // Loudness
    // Pitch
    // Prosody
    // HarmonicToNoiseRatio
    // Rythm
    // PausesDuration

    @Composable
    fun content(): @Composable ColumnScope.() -> Unit {
        return {

            Spacer(Modifier.height(15.dp))

            // ####### Displays ####### //

            miniDisplayBox(modelData,
                parameterId = "Loudness",
                d2ParameterId = "Pitch",
                d2NormalRangeMin = 175F, d2NormalRangeMax = 350F,
                d2IsEnableDeadZoneLow = true, d2IsEnableDeadZoneHigh = true)

            miniDisplayBox(modelData,
                parameterId = "Prosody", normalRangeMin = 0.25F, normalRangeMax = 0.75F,
                isEnableDeadZoneLow = true,
                d2ParameterId = "HarmonicToNoiseRatio",
                d2NormalRangeMin = 0.8F, d2IsEnableDeadZoneLow = true)

            miniDisplayBox(modelData,
                parameterId = "Rythm", normalRangeMax = 600F,
                d2ParameterId = "PausesDuration")

            // ####### Graphs ####### //

            // Get data
            val pointerPosition = modelData.pointerPosition.collectAsState().value
            val dataDurationSec = modelData.dataDurationSec.collectAsState().value
            val recordingState = modelData.recordingState.collectAsState().value

            // ======= Loudness ======= //

            val loudnessData = modelData.getGraphData("Loudness")
            graphNameText(modelData, "Loudness")
            Graph().draw(
                data = loudnessData,
                modelData = modelData,
                pointerPosition = pointerPosition,
                xLabelMax = dataDurationSec,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Pitch ======= //

            val pitchData = modelData.getGraphData("Pitch")
            graphNameText(modelData, "Pitch")
            Graph().draw(
                data = pitchData,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                yLabelMin = 50F,
                yLabelMax = 500F,
                horizontalLinesCount = 9,
                pointerPosition = pointerPosition,
                graphZones = listOf(
                    GraphZone(
                        minLabel = 175F,
                        maxLabel = 330F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

            // ======= Prosody ======= //

            val prosody = modelData.getGraphData("Prosody")
            graphNameText(modelData, "Prosody")
            Graph().draw(
                data = prosody,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                graphZones = listOf(
                    GraphZone(
                        minLabel = 0.25F,
                        maxLabel = 0.75F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= HarmonicToNoiseRatio ======= //

            val harmonicToNoiseRatio = modelData.getGraphData("HarmonicToNoiseRatio")
            graphNameText(modelData, "HarmonicToNoiseRatio")
            Graph().draw(
                data = harmonicToNoiseRatio,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                graphZones = listOf(
                    GraphZone(
                        minLabel = 0.8F,
                        maxLabel = 1F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Rythm ======= //

            val rythm = modelData.getGraphData("Rythm")
            graphNameText(modelData, "Rythm")
            Graph().draw(
                data = rythm,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                yLabelMin = 0F,
                yLabelMax = 600F,
                horizontalLinesCount = 30,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= PausesDuration ======= //

            val pausesDuration = modelData.getGraphData("PausesDuration")
            graphNameText(modelData, "PausesDuration")
            Graph().draw(
                data = pausesDuration,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // Bottom spacer

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}