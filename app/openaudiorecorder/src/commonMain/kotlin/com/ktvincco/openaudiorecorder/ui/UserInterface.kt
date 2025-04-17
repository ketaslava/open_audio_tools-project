package com.ktvincco.openaudiorecorder.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.ktvincco.openaudiorecorder.ColorPalette
import com.ktvincco.openaudiorecorder.MainApplicationTheme
import com.ktvincco.openaudiorecorder.presentation.ModelData
import com.ktvincco.openaudiorecorder.presentation.UiEventHandler
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.AllInfo
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.FemaleVoice
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.FemaleVoiceResonance
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.MaleVoice
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.MaleVoiceResonance
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.PitchAndResonance
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.Singing
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.SpeakerVoice
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.SpectrumInfo
import com.ktvincco.openaudiorecorder.ui.analysis_mode_pages.VoiceSmoothness
import com.ktvincco.openaudiorecorder.ui.pages.Dashboard
import com.ktvincco.openaudiorecorder.ui.pages.Reading
import com.ktvincco.openaudiorecorder.ui.pages.Recordings
import com.ktvincco.openaudiorecorder.ui.pages.VoiceChangeGuidelines


@Composable
expect fun getScreenSizeInDp(): Pair<Dp, Dp>
@Composable
expect fun getScreenSizeInPx(): Pair<Int, Int>


class UserInterface (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {


    @Composable
    fun draw() {
        MainApplicationTheme {
            Box (
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(ColorPalette.getBackgroundColor()),
            ) {
                // UI switch
                val isShowUi = modelData.isShowUi.collectAsState().value
                if (!isShowUi) { return@Box }

                // Front screens

                if (modelData.legalInfoScreenState.collectAsState().value) {
                    LegalInfoScreen(modelData, uiEventHandler).draw()
                    return@Box
                }

                // Draw pages
                val currentPage = modelData.currentPage.collectAsState().value

                when (currentPage) {
                    "FirstStartScreen" -> FirstStartScreen(modelData, uiEventHandler).draw()
                    "AccessDeniedScreen" -> AccessDeniedScreen(modelData, uiEventHandler).draw()

                    else -> mainScreen()
                }

                // Overlays

                // Loading screen overlay
                AnimatedVisibility (
                    modelData.isShowLoadingScreenOverlay.collectAsState().value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    LoadingScreenOverlay(modelData, uiEventHandler).draw()
                }

                // Help Menu overlay
                AnimatedVisibility (
                    modelData.helpMenuState.collectAsState().value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    HelpMenu(modelData, uiEventHandler).draw()
                }
            }
        }
    }


    @Composable
    fun mainScreen() {
        Box (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {

                        // Page
                        val currentPage = modelData.currentPage.collectAsState().value

                        if (currentPage == "Dashboard") {
                            Dashboard(modelData, uiEventHandler).draw()
                        }
                        if (currentPage == "VoiceChangeGuidelines") {
                            Page(modelData, uiEventHandler).draw(VoiceChangeGuidelines
                                (modelData, uiEventHandler).content(), false)
                        }

                        if (currentPage == "AllInfo") {
                            Page(modelData, uiEventHandler).draw(
                                AllInfo(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "SpectrumInfo") {
                            Page(modelData, uiEventHandler).draw(
                                SpectrumInfo(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "Reading") {
                            Reading(modelData, uiEventHandler).draw()
                        }
                        if (currentPage == "Recordings") {
                            Recordings(modelData, uiEventHandler).draw()
                        }

                        if (currentPage == "SpeakerVoice") {
                            Page(modelData, uiEventHandler).draw(
                                SpeakerVoice(modelData, uiEventHandler).content())
                        }

                        if (currentPage == "Singing") {
                            Page(modelData, uiEventHandler).draw(
                                Singing(modelData, uiEventHandler).content())
                        }

                        if (currentPage == "PitchAndResonance") {
                            Page(modelData, uiEventHandler).draw(
                                PitchAndResonance(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "VoiceSmoothness") {
                            Page(modelData, uiEventHandler).draw(
                                VoiceSmoothness(modelData, uiEventHandler).content())
                        }

                        if (currentPage == "FemaleVoice") {
                            Page(modelData, uiEventHandler).draw(
                                FemaleVoice(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "FemaleVoiceResonance") {
                            Page(modelData, uiEventHandler).draw(
                                FemaleVoiceResonance(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "MaleVoice") {
                            Page(modelData, uiEventHandler).draw(
                                MaleVoice(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "MaleVoiceResonance") {
                            Page(modelData, uiEventHandler).draw(
                                MaleVoiceResonance(modelData, uiEventHandler).content())
                        }

                        // Main Menu
                        Column(
                            Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            AnimatedVisibility(
                                modelData.mainMenuState.collectAsState().value,
                                enter = slideInVertically(initialOffsetY = {it * 2}) + fadeIn(),
                                exit = slideOutVertically(targetOffsetY = {it}) + fadeOut()
                            ) {
                                MainMenu(modelData, uiEventHandler).draw()
                            }
                        }
                    }
                }
                // Main Menu Bottom Bar
                MainMenu(modelData, uiEventHandler).openMainMenuButton()
            }

            Popup(modelData, uiEventHandler).Popup()
            Popup(modelData, uiEventHandler).PopupWithTextInput()
        }
    }
}
