package com.ktvincco.openaudiotools.ui

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
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.MainApplicationTheme
import com.ktvincco.openaudiotools.ui.basics.Popup
import com.ktvincco.openaudiotools.ui.pages.Dashboard
import com.ktvincco.openaudiotools.ui.pages.Recordings
import com.ktvincco.openaudiotools.ui.screens.AccessDeniedScreen
import com.ktvincco.openaudiotools.ui.screens.FirstStartScreen
import com.ktvincco.openaudiotools.ui.screens.LegalInfoScreen
import com.ktvincco.openaudiotools.ui.screens.LoadingScreenOverlay
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.AllInfo
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.FemaleVoice
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.FemaleVoiceResonance
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.MaleVoice
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.MaleVoiceResonance
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.PitchAndResonance
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.Singing
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.SpeakerVoice
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.SpectrumInfo
import com.ktvincco.openaudiotools.ui.analysis_mode_pages.VoiceSmoothness
import com.ktvincco.openaudiotools.ui.components.HelpMenu
import com.ktvincco.openaudiotools.ui.components.MainMenu
import com.ktvincco.openaudiotools.ui.components.PageWithBottomControls
import com.ktvincco.openaudiotools.ui.pages.Reading
import com.ktvincco.openaudiotools.ui.pages.SettingsPage
import com.ktvincco.openaudiotools.ui.pages.VoiceChangeGuidelines


class UserInterface (
    private val modelData: ModelData,
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
                    LegalInfoScreen(modelData).draw()
                    return@Box
                }

                // Draw pages
                val currentPage = modelData.currentPage.collectAsState().value

                when (currentPage) {
                    "FirstStartScreen" -> FirstStartScreen(modelData).draw()
                    "AccessDeniedScreen" -> AccessDeniedScreen(modelData).draw()

                    else -> mainScreen()
                }

                // Overlays

                // Loading screen overlay
                AnimatedVisibility (
                    modelData.isShowLoadingScreenOverlay.collectAsState().value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    LoadingScreenOverlay(modelData).draw()
                }

                // Help Menu overlay
                AnimatedVisibility (
                    modelData.helpMenuState.collectAsState().value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    HelpMenu(modelData).draw()
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
                            Dashboard(modelData).draw()
                        }
                        if (currentPage == "Settings") {
                            SettingsPage(modelData).draw()
                        }
                        if (currentPage == "VoiceChangeGuidelines") {
                            PageWithBottomControls(
                                modelData).draw(VoiceChangeGuidelines
                                (modelData).content(), false)
                        }

                        if (currentPage == "AllInfo") {
                            PageWithBottomControls(modelData).draw(
                                AllInfo(modelData).content())
                        }
                        if (currentPage == "SpectrumInfo") {
                            PageWithBottomControls(modelData).draw(
                                SpectrumInfo(modelData).content())
                        }
                        if (currentPage == "Reading") {
                            Reading(modelData).draw()
                        }
                        if (currentPage == "Recordings") {
                            Recordings(modelData).draw()
                        }

                        if (currentPage == "SpeakerVoice") {
                            PageWithBottomControls(modelData).draw(
                                SpeakerVoice(modelData).content())
                        }

                        if (currentPage == "Singing") {
                            PageWithBottomControls(modelData).draw(
                                Singing(modelData).content())
                        }

                        if (currentPage == "PitchAndResonance") {
                            PageWithBottomControls(modelData).draw(
                                PitchAndResonance(modelData).content())
                        }
                        if (currentPage == "VoiceSmoothness") {
                            PageWithBottomControls(modelData).draw(
                                VoiceSmoothness(modelData).content())
                        }

                        if (currentPage == "FemaleVoice") {
                            PageWithBottomControls(modelData).draw(
                                FemaleVoice(modelData).content())
                        }
                        if (currentPage == "FemaleVoiceResonance") {
                            PageWithBottomControls(modelData).draw(
                                FemaleVoiceResonance(modelData).content())
                        }
                        if (currentPage == "MaleVoice") {
                            PageWithBottomControls(modelData).draw(
                                MaleVoice(modelData).content())
                        }
                        if (currentPage == "MaleVoiceResonance") {
                            PageWithBottomControls(modelData).draw(
                                MaleVoiceResonance(modelData).content())
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
                                MainMenu(modelData).draw()
                            }
                        }
                    }
                }
                // Main Menu Bottom Bar
                MainMenu(modelData).openMainMenuButton()
            }

            Popup(modelData).Popup()
            Popup(modelData).PopupWithTextInput()
        }
    }
}
