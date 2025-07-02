package com.ktvincco.openaudiotools.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.DynamicText
import com.ktvincco.openaudiotools.presentation.ModelData


class AccessDeniedScreen (
    private val modelData: ModelData
) {

    @Composable
    fun draw() {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {

                DynamicText(
                    text = "Access denied\n\n" +
                            "To use this application, you need to grant all required permissions.\n\n" +
                            "To grant permissions, OPEN the app permission SETTINGS and PROVIDE all necessary PERMISSIONS, then RESTART the APPLICATION.\n\n",
                    modelData = modelData,
                    textAlign = TextAlign.Center,
                    lineHeight = TextUnit(28F, TextUnitType.Sp),
                    fontSize = TextUnit(18F, TextUnitType.Sp),
                    color = ColorPalette.getTextColor()
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(64.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(ColorPalette.getButtonColor())
                        .clickable {
                            modelData.openAppPermissionSettingsButtonClicked()
                        }
                ) {
                    DynamicText(
                        text = "Open Configuration",
                        modelData = modelData,
                        color = ColorPalette.getTextColor(),
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(64.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(ColorPalette.getButtonColor())
                        .clickable {
                            modelData.restartAppButtonClicked()
                        }
                ) {
                    DynamicText(
                        text = "Restart",
                        modelData = modelData,
                        color = ColorPalette.getTextColor(),
                    )
                }
            }
        }
    }
}