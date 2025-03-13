package com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ktvincco.openvoiceanalyzer.ColorPalette
import com.ktvincco.openvoiceanalyzer.Settings
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler
import com.ktvincco.openvoiceanalyzer.ui.Graph
import com.ktvincco.openvoiceanalyzer.ui.GraphZone
import com.ktvincco.openvoiceanalyzer.ui.graphNameText
import com.ktvincco.openvoiceanalyzer.ui.miniDisplayBox


class MaleVoiceResonance (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    // Loudness
    // Pitch
    // VoiceWeight
    // FirstFormant A
    // SecondFormant A
    // FirstFormant I
    // SecondFormant I
    // FirstFormant U
    // SecondFormant U

    @Composable
    fun content(): @Composable ColumnScope.() -> Unit {
        return {

            Spacer(Modifier.height(15.dp))

            // ####### Displays ####### //

            miniDisplayBox(modelData, uiEventHandler, parameterId = "Loudness")

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Pitch", normalRangeMin = 50F,
                normalRangeMax = 150F, isEnableDeadZoneLow = true, isEnableDeadZoneHigh = true,
                d2ParameterId = "VoiceWeight",
                d2NormalRangeMin = 0.25F, d2NormalRangeMax = 0.75F, d2IsEnableDeadZoneLow = true)

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
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Pitch ======= //

            val pitchData = modelData.getGraphData("Pitch")
            graphNameText(modelData, "Pitch")
            Graph().draw(
                data = pitchData,
                xLabelMax = dataDurationSec,
                yLabelMin = 50F,
                yLabelMax = 500F,
                horizontalLinesCount = 9,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                graphZones = listOf(
                    GraphZone(
                        minLabel = 50F,
                        maxLabel = 150F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= VoiceWeight ======= //

            val voiceWeight = modelData.getGraphData("VoiceWeight")
            graphNameText(modelData, "VoiceWeight")
            Graph().draw(
                data = voiceWeight,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                graphZones = listOf(
                    GraphZone(
                        minLabel = 0.25F,
                        maxLabel = 0.75F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // A

            // ======= Additional Displays ======= //

            Spacer(Modifier.height(15.dp))

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Pitch",
                normalRangeMin = 50F, normalRangeMax = 150F,
                isEnableDeadZoneLow = true, isEnableDeadZoneHigh = true)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "FirstFormant",
                normalRangeMin = 500F, normalRangeMax = 700F,
                isEnableDeadZoneLow = true, isEnableDeadZoneHigh = true,
                d2ParameterId = "SecondFormant",
                d2NormalRangeMin = 900F, d2NormalRangeMax = 1200F,
                d2IsEnableDeadZoneLow = true, d2IsEnableDeadZoneHigh = true
            )

            // ======= First Formant ======= //

            val firstFormantGraph = modelData.getGraphData("FirstFormant")
            graphNameText(modelData, "FirstFormant", nameAddition = " for >>A<< ")
            Graph().draw(
                data = firstFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                graphZones = listOf(
                    GraphZone(
                        minLabel = 500F,
                        maxLabel = 700F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= Second Formant ======= //

            val secondFormantGraph = modelData.getGraphData("SecondFormant")
            graphNameText(modelData, "SecondFormant", nameAddition = " for >>A<< ")
            Graph().draw(
                data = secondFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                graphZones = listOf(
                    GraphZone(
                        minLabel = 900F,
                        maxLabel = 1200F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // I

            // ======= Additional Displays ======= //

            Spacer(Modifier.height(15.dp))

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Pitch",
                normalRangeMin = 50F, normalRangeMax = 150F,
                isEnableDeadZoneLow = true, isEnableDeadZoneHigh = true)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "FirstFormant",
                normalRangeMin = 150F, normalRangeMax = 350F,
                isEnableDeadZoneLow = true, isEnableDeadZoneHigh = true,
                d2ParameterId = "SecondFormant",
                d2NormalRangeMin = 1800F, d2NormalRangeMax = 2400F,
                d2IsEnableDeadZoneLow = true, d2IsEnableDeadZoneHigh = true
            )

            // ======= First Formant ======= //

            graphNameText(modelData, "FirstFormant", nameAddition = " for >>I<< ")
            Graph().draw(
                data = firstFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                graphZones = listOf(
                    GraphZone(
                        minLabel = 150F,
                        maxLabel = 350F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= Second Formant ======= //

            graphNameText(modelData, "SecondFormant", nameAddition = " for >>I<< ")
            Graph().draw(
                data = secondFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                graphZones = listOf(
                    GraphZone(
                        minLabel = 1800F,
                        maxLabel = 2400F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // U

            // ======= Additional Displays ======= //

            Spacer(Modifier.height(15.dp))

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Pitch",
                normalRangeMin = 50F, normalRangeMax = 150F,
                isEnableDeadZoneLow = true, isEnableDeadZoneHigh = true)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "FirstFormant",
                normalRangeMin = 250F, normalRangeMax = 400F,
                isEnableDeadZoneLow = true, isEnableDeadZoneHigh = true,
                d2ParameterId = "SecondFormant",
                d2NormalRangeMin = 600F, d2NormalRangeMax = 900F,
                d2IsEnableDeadZoneLow = true, d2IsEnableDeadZoneHigh = true
            )

            // ======= First Formant ======= //

            graphNameText(modelData, "FirstFormant", nameAddition = " for >>U<< ")
            Graph().draw(
                data = firstFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                graphZones = listOf(
                    GraphZone(
                        minLabel = 250F,
                        maxLabel = 400F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= Second Formant ======= //

            graphNameText(modelData, "SecondFormant", nameAddition = " for >>U<< ")
            Graph().draw(
                data = secondFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                graphZones = listOf(
                    GraphZone(
                        minLabel = 600F,
                        maxLabel = 900F,
                        color = ColorPalette.getSoftGreenColor().copy(alpha = 0.25F)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // Bottom spacer

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}