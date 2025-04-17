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
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.presentation.UiEventHandler
import com.ktvincco.openaudiotools.ui.Graph
import com.ktvincco.openaudiotools.ui.NoteGraph
import com.ktvincco.openaudiotools.ui.graphNameText


class Singing (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    // NoteGraph
    // Loudness
    // Rythm
    // VoiceWeight
    // Clarity
    // Jitter
    // Shimmer

    @Composable
    fun content(): @Composable ColumnScope.() -> Unit {
        return {

            // ####### Graphs ####### //

            // Get data
            val pitchData = modelData.getGraphData("Pitch")
            val pointerPosition = modelData.pointerPosition.collectAsState().value
            val dataDurationSec = modelData.dataDurationSec.collectAsState().value
            val recordingState = modelData.recordingState.collectAsState().value

            // ======= Note Graph ======= //

            graphNameText(modelData, "NoteGraph")

            NoteGraph().draw(
                data = pitchData,
                xLabelMin = 0F,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1024.dp)
            )

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

            // ======= Rythm ======= //

            val rythm = modelData.getGraphData("Rythm")
            graphNameText(modelData, "Rythm")
            Graph().draw(
                data = rythm,
                xLabelMax = dataDurationSec,
                yLabelMin = 0F,
                yLabelMax = 600F,
                horizontalLinesCount = 30,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Clarity ======= //

            val clarity = modelData.getGraphData("Clarity")
            graphNameText(modelData, "Clarity")
            Graph().draw(
                data = clarity,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Jitter ======= //

            val jitter = modelData.getGraphData("Jitter")
            graphNameText(modelData, "Jitter")
            Graph().draw(
                data = jitter,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Shimmer ======= //

            val shimmer = modelData.getGraphData("Shimmer")
            graphNameText(modelData, "Shimmer")
            Graph().draw(
                data = shimmer,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // Bottom spacer

            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}