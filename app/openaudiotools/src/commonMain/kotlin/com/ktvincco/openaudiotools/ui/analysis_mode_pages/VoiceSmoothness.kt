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


class VoiceSmoothness (
    private val modelData: ModelData
) {

    // Loudness
    // Jitter
    // Shimmer
    // Clarity
    // VoiceWeight

    @Composable
    fun content(): @Composable ColumnScope.() -> Unit {
        return {

            Spacer(Modifier.height(15.dp))

            // ####### Displays ####### //

            miniDisplayBox(modelData, parameterId = "Loudness")

            miniDisplayBox(modelData,
                parameterId = "Jitter", d2ParameterId = "Shimmer")

            miniDisplayBox(modelData,
                parameterId = "Clarity", d2ParameterId = "VoiceWeight")

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

            // ======= Jitter ======= //

            val jitter = modelData.getGraphData("Jitter")
            graphNameText(modelData, "Jitter")
            Graph().draw(
                data = jitter,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Shimmer ======= //

            val shimmer = modelData.getGraphData("Shimmer")
            graphNameText(modelData, "Shimmer")
            Graph().draw(
                data = shimmer,
                modelData = modelData,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Configuration.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Clarity ======= //

            val clarity = modelData.getGraphData("Clarity")
            graphNameText(modelData, "Clarity")
            Graph().draw(
                data = clarity,
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

            // Bottom spacer

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}