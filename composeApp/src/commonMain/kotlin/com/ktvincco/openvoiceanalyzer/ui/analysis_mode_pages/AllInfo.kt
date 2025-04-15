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
import com.ktvincco.openvoiceanalyzer.ui.graphNameText
import com.ktvincco.openvoiceanalyzer.ui.miniDisplayBox


class AllInfo (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    @Composable
    fun content(): @Composable ColumnScope.() -> Unit {
        return {

            Spacer(Modifier.height(15.dp))

            // ####### Displays ####### //

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Loudness",
                d2ParameterId = "Pitch", d2NormalRangeMax = 500F)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "FirstFormant", normalRangeMax = 4096F,
                d2ParameterId = "SecondFormant", d2NormalRangeMax = 4096F)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "ActiveFirstFormant", normalRangeMax = 4096F,
                d2ParameterId = "ActiveSecondFormant", d2NormalRangeMax = 4096F)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Energy",
                d2ParameterId = "H1H2EnergyBalance",
                d2IsEnableNegativeValues = true, d2NormalRangeMin = -1F)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "VoiceWeight",
                d2ParameterId = "HarmonicToNoiseRatio")

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "BandEnergyRatioLow",
                d2ParameterId = "BandEnergyRatioMedium")

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "BandEnergyRatioHigh",
                d2ParameterId = "HLRatio", d2IsEnableNegativeValues = true)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "VAD",
                d2ParameterId = "SpectralCentroid", d2NormalRangeMax = 4096F)

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "SpectralTilt",
                isEnableNegativeValues = true, normalRangeMin = -1F,
                d2ParameterId = "SpectralSpread")

            miniDisplayBox(modelData, uiEventHandler,
                parameterId = "Jitter",
                d2ParameterId = "Shimmer")

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
                pointerPosition = pointerPosition,
                xLabelMax = dataDurationSec,
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

            // ======= First Formant ======= //

            val firstFormantGraph = modelData.getGraphData("FirstFormant")
            graphNameText(modelData, "FirstFormant")
            Graph().draw(
                data = firstFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= Second Formant ======= //

            val secondFormantGraph = modelData.getGraphData("SecondFormant")
            graphNameText(modelData, "SecondFormant")
            Graph().draw(
                data = secondFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= Active First Formant ======= //

            val activeFirstFormantGraph = modelData.getGraphData("ActiveFirstFormant")
            graphNameText(modelData, "ActiveFirstFormant")
            Graph().draw(
                data = activeFirstFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= Active Second Formant ======= //

            val activeSecondFormantGraph = modelData.getGraphData("ActiveSecondFormant")
            graphNameText(modelData, "ActiveSecondFormant")
            Graph().draw(
                data = activeSecondFormantGraph,
                xLabelMax = dataDurationSec,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            // ======= Energy ======= //

            val energy = modelData.getGraphData("Energy")
            graphNameText(modelData, "Energy")
            Graph().draw(
                data = energy,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= H1H2EnergyBalance ======= //

            val h1h2EnergyBalance = modelData.getGraphData("H1H2EnergyBalance")
            graphNameText(modelData, "H1H2EnergyBalance")
            Graph().draw(
                data = h1h2EnergyBalance,
                xLabelMax = dataDurationSec,
                yLabelMin = -1F,
                yLabelMax = 1F,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= HarmonicToNoiseRatio ======= //

            val harmonicToNoiseRatio = modelData.getGraphData("HarmonicToNoiseRatio")
            graphNameText(modelData, "HarmonicToNoiseRatio")
            Graph().draw(
                data = harmonicToNoiseRatio,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= BandEnergyRatioLow ======= //

            val bandEnergyRatioLow = modelData.getGraphData("BandEnergyRatioLow")
            graphNameText(modelData, "BandEnergyRatioLow")
            Graph().draw(
                data = bandEnergyRatioLow,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= BandEnergyRatioMedium ======= //

            val bandEnergyRatioMedium = modelData.getGraphData(
                "BandEnergyRatioMedium"
            )
            graphNameText(modelData, "BandEnergyRatioMedium")
            Graph().draw(
                data = bandEnergyRatioMedium,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= BandEnergyRatioHigh ======= //

            val bandEnergyRatioHigh = modelData.getGraphData(
                "BandEnergyRatioHigh"
            )
            graphNameText(modelData, "BandEnergyRatioHigh")
            Graph().draw(
                data = bandEnergyRatioHigh,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= HLRatio ======= //

            val hlRatio = modelData.getGraphData("HLRatio")
            graphNameText(modelData, "HLRatio")
            Graph().draw(
                data = hlRatio,
                yLabelMin = -1F,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= VAD ======= //

            val vad = modelData.getGraphData("VAD")
            graphNameText(modelData, "VAD")
            /*Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                graphColorDescriptionBlock(Color.Green, "Okay")
                graphColorDescriptionBlock(Color.Red, "Not Okay")
            }*/
            Graph().draw(
                data = vad,
                xLabelMax = dataDurationSec,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= SpectralCentroid ======= //

            val spectralCentroid = modelData.getGraphData("SpectralCentroid")
            graphNameText(modelData, "SpectralCentroid")
            Graph().draw(
                data = spectralCentroid,
                xLabelMax = dataDurationSec,
                yLabelMin = 0F,
                yLabelMax = 4096F,
                horizontalLinesCount = 16,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )

            // ======= SpectralTilt ======= //

            val spectralTilt = modelData.getGraphData("SpectralTilt")
            graphNameText(modelData, "SpectralTilt")
            Graph().draw(
                data = spectralTilt,
                xLabelMax = dataDurationSec,
                yLabelMin = -1F,
                yLabelMax = 1F,
                pointerPosition = pointerPosition,
                isEnableAutoScroll = recordingState,
                autoScrollXWindowSize = Settings.getAutoScrollXWindowSize(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // ======= SpectralSpread ======= //

            val spectralSpread = modelData.getGraphData("SpectralSpread")
            graphNameText(modelData, "SpectralSpread")
            Graph().draw(
                data = spectralSpread,
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