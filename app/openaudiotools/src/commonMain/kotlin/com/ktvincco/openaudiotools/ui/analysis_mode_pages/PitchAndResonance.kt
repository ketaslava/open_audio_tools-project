package com.ktvincco.openaudiotools.ui.analysis_mode_pages

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.Configuration
import com.ktvincco.openaudiotools.ui.charts.Graph
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.ui.components.graphNameText
import com.ktvincco.openaudiotools.ui.components.miniDisplayBox


class PitchAndResonance (
    private val modelData: ModelData
) {

    // Loudness
    // VoiceWeight
    // Pitch
    // FirstFormant
    // SecondFormant

    @Composable
    fun content(): @Composable ColumnScope.() -> Unit {
        return {

            Spacer(Modifier.height(15.dp))

            // ####### Displays ####### //

            miniDisplayBox(modelData, parameterId = "Loudness")

            miniDisplayBox(modelData,
                parameterId = "VoiceWeight",
                d2ParameterId = "Pitch", d2NormalRangeMax = 500F)

            miniDisplayBox(modelData,
                parameterId = "FirstFormant", normalRangeMax = 4096F,
                d2ParameterId = "SecondFormant", d2NormalRangeMax = 4096F)

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
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= VoiceWeight ======= //

            val voiceWeight = modelData.getGraphData("VoiceWeight")
            graphNameText(modelData, "VoiceWeight")
            Graph().draw(
                data = voiceWeight,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
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
                horizontalLinesCount = 18,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= First Formant ======= //

            val firstFormantGraph = modelData.getGraphData("FirstFormant")
            graphNameText(modelData, "FirstFormant")
            Graph().draw(
                data = firstFormantGraph,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= Second Formant ======= //

            val secondFormantGraph = modelData.getGraphData("SecondFormant")
            graphNameText(modelData, "SecondFormant")
            Graph().draw(
                data = secondFormantGraph,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // Bottom spacer

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}