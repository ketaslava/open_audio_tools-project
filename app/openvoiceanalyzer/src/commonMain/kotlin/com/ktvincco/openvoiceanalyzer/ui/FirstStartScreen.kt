package com.ktvincco.openvoiceanalyzer.ui

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.Settings
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler


class FirstStartScreen (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    @Composable
    fun draw() {

        var isCheckboxTermsOfUseChecked by remember { mutableStateOf(false) }
        var isCheckboxPrivacyPolicyChecked by remember { mutableStateOf(false) }

        AnimatedVisibility(true) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier =  Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)

                ) {
                    val scrollState = rememberScrollState()

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(state = scrollState)
                    ) {
                        Text(
                            text = "Open Voice Analyzer",
                            color = ColorPalette.getTextColor(),
                            fontSize = 24.sp,
                            lineHeight = 24.sp,
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 24.dp)
                        )

                        BaseComponents().HorizontalDivider(
                            color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                        Text(
                            text = "To use this application\n" +
                                    "you need to read and agree with our\n" +
                                    "Terms Of Use and Privacy Policy",
                            textAlign = TextAlign.Center,
                            lineHeight = TextUnit(24F, TextUnitType.Sp),
                            fontSize = TextUnit(16F, TextUnitType.Sp),
                            color = ColorPalette.getTextColor(),
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 24.dp)
                        )

                        BaseComponents().HorizontalDivider(
                            color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Terms Of Use",
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            color = ColorPalette.getTextColor(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { uiEventHandler.openWebLinkButtonCallbackClicked(
                                        Settings.getTermsOfUseWebLink()) }
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        Text(
                            text = "Privacy Policy",
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            color = ColorPalette.getTextColor(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { uiEventHandler.openWebLinkButtonCallbackClicked(
                                    Settings.getPrivacyPolicyWebLink()) }
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        Text(
                            text = "Legal Info",
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            color = ColorPalette.getTextColor(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { modelData.setLegalInfoScreenState(true) }
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        BaseComponents().HorizontalDivider(
                            color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                        Spacer(modifier = Modifier.height(24.dp))

                        BaseComponents().Checkbox(
                            "I have read and accept the Terms of Use", Modifier) { state ->
                            isCheckboxTermsOfUseChecked = state
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        BaseComponents().Checkbox(
                            "I have read and accept the Privacy Policy", Modifier) { state ->
                            isCheckboxPrivacyPolicyChecked = state
                        }

                        // Bottom spacer
                        Spacer(modifier = Modifier.height(64.dp))
                    }
                }

                // Button appearance changes when checkboxes is not checked
                var buttonColor = ColorPalette.getButtonColor()
                var textColor = ColorPalette.getTextColor()
                if (!isCheckboxTermsOfUseChecked || !isCheckboxPrivacyPolicyChecked) {
                    buttonColor = ColorPalette.getButtonColor().copy(alpha = 0.33F)
                    textColor = ColorPalette.getTextColor().copy(alpha = 0.33F)
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(64.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(buttonColor)
                        .clickable {
                            tryToContinue(
                                isCheckboxTermsOfUseChecked, isCheckboxPrivacyPolicyChecked)
                        }
                ) {
                    Text(
                        text = "Continue",
                        color = textColor,
                    )
                }
            }
        }
    }


    private fun tryToContinue(state1: Boolean, state2: Boolean) {
        if (state1 and state2) {
            modelData.firstStartScreenClosed()
        }
    }

}