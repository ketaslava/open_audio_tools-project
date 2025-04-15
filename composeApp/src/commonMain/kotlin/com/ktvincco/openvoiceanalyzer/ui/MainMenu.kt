package com.ktvincco.openvoiceanalyzer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openvoiceanalyzer.ColorPalette
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler
import openvoiceanalyzer.composeapp.generated.resources.Res
import openvoiceanalyzer.composeapp.generated.resources.menu_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import org.jetbrains.compose.resources.painterResource

class MainMenu (private val modelData: ModelData, private val uiEventHandler: UiEventHandler) {


    @Composable
    fun openMainMenuButton() {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(ColorPalette.getBlockColor())
                .clickable { modelData.switchMainMenuState() }
                .padding(horizontal = 32.dp)
        ) {
            var text = modelData.currentPage.collectAsState().value

            if (text == "VoiceChangeGuidelines") {
                text = "Voice Change Guidelines"
            }
            if (text == "AllInfo") {
                text = "All Info"
            }
            if (text == "AboutApp") {
                text = "About App"
            }
            if (text == "SpeakerVoice") {
                text = "Speaker Voice"
            }
            if (text == "PitchAndResonance") {
                text = "Pitch And Resonance"
            }
            if (text == "VoiceSmoothness") {
                text = "Voice Smoothness"
            }
            if (text == "FemaleVoice") {
                text = "Female Voice"
            }
            if (text == "FemaleVoiceResonance") {
                text = "Female Voice Resonance"
            }
            if (text == "MaleVoice") {
                text = "Male Voice"
            }
            if (text == "MaleVoiceResonance") {
                text = "Male Voice Resonance"
            }

            Text(
                text = text,
                color = ColorPalette.getTextColor(),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            Image(
                painterResource(Res.drawable.menu_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                null,
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
            )
        }
    }


    @Composable
    fun draw() {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(state = ScrollState(0))
                .background(ColorPalette.getBlockColor()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Main Menu",
                color = ColorPalette.getTextColor(),
                fontSize = 24.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(state = ScrollState(0)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                menuItem("Dashboard") {
                    modelData.openDashboardPage()
                    modelData.setMainMenuState(false)
                }

                menuItem("Voice Change Guidelines") {
                    modelData.openVoiceChangeGuidelinesPage()
                    modelData.setMainMenuState(false)
                }

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                menuItem("All Info") {
                    modelData.openAllInfoPage()
                    modelData.setMainMenuState(false)
                }

                menuItem("SpectrumInfo") {
                    modelData.openSpectrumInfoPage()
                    modelData.setMainMenuState(false)
                }

                menuItem("Reading") {
                    modelData.openReadingPage()
                    modelData.setMainMenuState(false)
                }

                menuItem("Recordings") {
                    modelData.openRecordingsPage()
                    modelData.setMainMenuState(false)
                }

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                menuItem("Speaker Voice") {
                    modelData.openSpeakerVoicePage()
                    modelData.setMainMenuState(false)
                }

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                menuItem("Singing") {
                    modelData.openSingingPage()
                    modelData.setMainMenuState(false)
                }

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                menuItem("Pitch And Resonance") {
                    modelData.openPitchAndResonancePage()
                    modelData.setMainMenuState(false)
                }

                menuItem("Voice Smoothness") {
                    modelData.openVoiceSmoothnessPage()
                    modelData.setMainMenuState(false)
                }

                BaseComponents().HorizontalDivider(
                    color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                menuItem("Female Voice") {
                    modelData.openFemaleVoicePage()
                    modelData.setMainMenuState(false)
                }

                menuItem("Female Voice Resonance") {
                    modelData.openFemaleVoiceResonancePage()
                    modelData.setMainMenuState(false)
                }

                menuItem("Male Voice") {
                    modelData.openMaleVoicePage()
                    modelData.setMainMenuState(false)
                }

                menuItem("Male Voice Resonance") {
                    modelData.openMaleVoiceResonancePage()
                    modelData.setMainMenuState(false)
                }
            }
        }
    }

    @Composable
    fun menuItem(text: String, callback: () -> Unit) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { callback.invoke() }
                .padding(16.dp)
        ) {
            Text(
                text = text,
                color = ColorPalette.getTextColor(),
                style = MaterialTheme.typography.body1
            )
        }
    }

}