package com.ktvincco.openaudiotools.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.DynamicText
import com.ktvincco.openaudiotools.ui.basics.BaseComponents
import com.ktvincco.openaudiotools.presentation.ModelData


class VoiceChangeGuidelines (
    private val modelData: ModelData
) {

    @Composable
    fun content(): @Composable ColumnScope.() -> Unit {
        return {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                DynamicText(
                    text = "Voice Change Guidelines",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "If you want to change your voice, this app is here to help you achieve your goals. Follow these steps to get the best results:",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp
                )

                DynamicText(
                    text = "Step 1: Choose Your Scenario",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "To start, decide on the purpose of your voice modification.\n" +
                            "\n" +
                            " * For specific scenarios like Singing, Public Speaking, or Gender-Affirming Voice Change, pre-configured modes are available. These modes group relevant parameters, making it easier to focus on what matters most.\n" +
                            "\n" +
                            " * Alternatively, you can use the All Info mode, which provides detailed access to all available voice parameters for in-depth analysis.",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp
                )

                DynamicText(
                    text = "Step 2: Define Your Goal",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "Having a clear goal is crucial for success.\n" +
                            "\n" +
                            " * For Gender-Affirming Voice Change, aim to adjust your voice so that its graphs fall within the highlighted green zones. This indicates a natural-sounding voice.\n" +
                            "\n" +
                            " * For other scenarios, reflect on what you want to achieve. Understand which parameters influence your desired outcome. Use the Documentation button next to each parameter for explanations and tips.",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp
                )

                DynamicText(
                    text = "Step 3: Record Your Voice",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "Record a 30-second sample of your voice for the app to analyze. If you're unsure what to say, use the Reading tab, which provides text to read.",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp
                )

                DynamicText(
                    text = "Step 4: Identify Areas for Improvement",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "Review the graphs generated by the app to see which aspects of your voice could be adjusted.\n" +
                            "\n" +
                            " * Experiment with modifying your voice and record another sample to see how the changes affect the analysis.\n" +
                            "\n" +
                            " * Repeat this process until you reach your desired outcome.",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp
                )

                DynamicText(
                    text = "Real-Time Feedback",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )

                DynamicText(
                    text = "For quicker adjustments, you can monitor the graphs while recording and make real-time corrections to your voice.\n" +
                            "\n" +
                            "With patience and practice, this app can help you understand your voice better and guide you toward achieving your unique vocal goals.",
                    modelData = modelData,
                    color = ColorPalette.getTextColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                )

                // Bottom spacer

                Spacer(modifier = Modifier.height(86.dp))
            }
        }
    }
}