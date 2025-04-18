package com.ktvincco.openvoiceanalyzer.ui

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
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.AllInfo
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.FemaleVoice
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.FemaleVoiceResonance
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.MaleVoice
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.MaleVoiceResonance
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.PitchAndResonance
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.Singing
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.SpeakerVoice
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.SpectrumInfo
import com.ktvincco.openvoiceanalyzer.ui.analysis_mode_pages.VoiceSmoothness
import com.ktvincco.openvoiceanalyzer.ui.components.HelpMenu
import com.ktvincco.openvoiceanalyzer.ui.components.MainMenu
import com.ktvincco.openvoiceanalyzer.ui.components.PageWithBottomControls
import com.ktvincco.openvoiceanalyzer.ui.pages.Reading
import com.ktvincco.openvoiceanalyzer.ui.pages.VoiceChangeGuidelines


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
                            PageWithBottomControls(
                                modelData, uiEventHandler).draw(VoiceChangeGuidelines
                                (modelData, uiEventHandler).content(), false)
                        }

                        if (currentPage == "AllInfo") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
                                AllInfo(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "SpectrumInfo") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
                                SpectrumInfo(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "Reading") {
                            Reading(modelData, uiEventHandler).draw()
                        }
                        if (currentPage == "Recordings") {
                            Recordings(modelData, uiEventHandler).draw()
                        }

                        if (currentPage == "SpeakerVoice") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
                                SpeakerVoice(modelData, uiEventHandler).content())
                        }

                        if (currentPage == "Singing") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
                                Singing(modelData, uiEventHandler).content())
                        }

                        if (currentPage == "PitchAndResonance") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
                                PitchAndResonance(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "VoiceSmoothness") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
                                VoiceSmoothness(modelData, uiEventHandler).content())
                        }

                        if (currentPage == "FemaleVoice") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
                                FemaleVoice(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "FemaleVoiceResonance") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
                                FemaleVoiceResonance(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "MaleVoice") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
                                MaleVoice(modelData, uiEventHandler).content())
                        }
                        if (currentPage == "MaleVoiceResonance") {
                            PageWithBottomControls(modelData, uiEventHandler).draw(
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
