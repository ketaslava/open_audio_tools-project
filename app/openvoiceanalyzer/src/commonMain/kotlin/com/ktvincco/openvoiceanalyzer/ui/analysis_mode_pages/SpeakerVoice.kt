package com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.Settings
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler
import com.ktvincco.openvoiceanalyzer.ui.Graph
import com.ktvincco.openvoiceanalyzer.ui.graphNameText
import com.ktvincco.openvoiceanalyzer.ui.miniDisplayBox


class SpeakerVoice (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    // Loudness
    // Pitch
    // Prosody
    // Rythm
    // Clarity
    // PausesDuration

    @Composable
    fun content(): @Composable ColumnScope.() -> Unit {
        return {

            Spacer(Modifier.height(15.dp))

            // ####### Displays ####### //

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Loudness",
                d2ParameterId = "Pitch", d2NormalRangeMax = 500F)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Prosody",
                d2ParameterId = "Rythm", d2NormalRangeMax = 600F)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Clarity",
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= Prosody ======= //

            val prosody = modelData.getGraphData("Prosody")
            graphNameText(modelData, "Prosody")
            Graph().draw(
                data = prosody,
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

            // ======= PausesDuration ======= //

            val pausesDuration = modelData.getGraphData("PausesDuration")
            graphNameText(modelData, "PausesDuration")
            Graph().draw(
                data = pausesDuration,
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