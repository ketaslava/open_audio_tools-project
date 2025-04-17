package com.ktvincco.openaudiorecorder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiorecorder.ColorPalette
import com.ktvincco.openaudiorecorder.presentation.ModelData
import com.ktvincco.openaudiorecorder.presentation.UiEventHandler


class Popup (private val modelData: ModelData, private val uiEventHandler: UiEventHandler) {

    @Composable
    fun Popup() {

        val isPopupOpened = modelData.isPopupOpened.collectAsState().value
        val popupHeadline = modelData.popupHeadline.collectAsState().value
        val popupText = modelData.popupText.collectAsState().value

        // Switch
        if (!isPopupOpened) { return }

        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(
                    onClick = {},
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.8f)
                    .wrapContentHeight()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(ColorPalette.getBlockColor())
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = popupHeadline,
                    style = MaterialTheme.typography.h6,
                    color = ColorPalette.getTextColor()
                )

                Spacer(Modifier.height(42.dp))

                Text(
                    text = popupText,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = ColorPalette.getTextColor()
                )

                Spacer(Modifier.height(42.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement =  Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =  Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(ColorPalette.getLightButtonColor())
                            .weight(1F)
                            .clickable { modelData.closePopup("Cancel") }
                    ) {
                        Text(
                            text = "Cancel",
                            color = ColorPalette.getTextColor()
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .width(16.dp)
                    )
                    Column(
                        verticalArrangement =  Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(ColorPalette.getLightButtonColor())
                            .weight(1F)
                            .clickable { modelData.closePopup("Ok") }
                    ) {
                        Text(
                            text = "Ok",
                            color = ColorPalette.getTextColor()
                        )
                    }
                }
            }
        }
    }


    @Composable
    fun PopupWithTextInput() {

        val isPopupWithTextInputOpened =
            modelData.isPopupWithTextInputOpened.collectAsState().value
        val popupHeadline =
            modelData.popupHeadline.collectAsState().value
        var textInput by remember { mutableStateOf("") }

        // Switch
        if (!isPopupWithTextInputOpened) { return }

        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(
                    onClick = {},
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.8f)
                    .wrapContentHeight()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(ColorPalette.getBlockColor())
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = popupHeadline,
                    style = MaterialTheme.typography.h6,
                    color = ColorPalette.getTextColor()
                )

                Spacer(Modifier.height(42.dp))

                TextField(
                    value = textInput,
                    onValueChange = { textInput = it },
                    placeholder = { Text("Enter the text") },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = ColorPalette.getTextColor(),
                        backgroundColor = ColorPalette.getBlockColor(),
                        cursorColor = ColorPalette.getTextColor(),
                        placeholderColor = ColorPalette.getTextColor()
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(ColorPalette.getLightButtonColor())
                )

                Spacer(Modifier.height(42.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(ColorPalette.getLightButtonColor())
                            .weight(1F)
                            .clickable {
                                modelData.closePopupWithTextInput("Cancel", textInput)
                                textInput = ""
                            }
                    ) {
                        Text(
                            text = "Cancel",
                            color = ColorPalette.getTextColor()
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .width(16.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(ColorPalette.getLightButtonColor())
                            .weight(1F)
                            .clickable {
                                modelData.closePopupWithTextInput("Ok", textInput)
                                textInput = ""
                            }
                    ) {
                        Text(
                            text = "Ok",
                            color = ColorPalette.getTextColor()
                        )
                    }
                }
            }
        }
    }
}