package com.ktvincco.openaudiorecorder.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiorecorder.getScreenSizeInPx
import com.ktvincco.openaudiorecorder.presentation.ModelData
import com.ktvincco.openaudiorecorder.presentation.UiEventHandler
import com.ktvincco.openaudiotools.ColorPalette
import openaudiotools.common.openaudiotools.generated.resources.Res
import openaudiotools.common.openaudiotools.generated.resources.arrow_back_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openaudiotools.common.openaudiotools.generated.resources.arrow_forward_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openaudiotools.common.openaudiotools.generated.resources.close_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openaudiotools.common.openaudiotools.generated.resources.delete_forever_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openaudiotools.common.openaudiotools.generated.resources.download_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openaudiotools.common.openaudiotools.generated.resources.pause_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import openaudiotools.common.openaudiotools.generated.resources.play_arrow_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
import org.jetbrains.compose.resources.painterResource


class RecordingControl (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {


    @Composable
    fun draw() {
        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            // Get layout
            val layout = modelData.recordingControlLayout.collectAsState().value

            // Layout

            if (layout == "ReadyToRecording") {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(ColorPalette.getSoftGreenColor())
                        .clickable { uiEventHandler.recordButtonClicked() }
                ) {
                    Text(
                        text = "Start recording",
                        textAlign = TextAlign.Center,
                        lineHeight = TextUnit(28F, TextUnitType.Sp),
                        fontSize = TextUnit(18F, TextUnitType.Sp),
                        color = ColorPalette.getTextColor()
                    )
                }
            }

            if (layout == "Recording") {

                // Get data
                val recordingQuality = modelData.recordingQuality.collectAsState().value
                val state = recordingQuality.getState()
                val text = recordingQuality.getText()

                // Set color
                var buttonColor = ColorPalette.getSoftGreenColor()
                if (state == "Medium") {
                    buttonColor = ColorPalette.getSoftYellowColor()
                }
                if (state == "Bad") {
                    buttonColor = ColorPalette.getSoftRedColor()
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(buttonColor)
                        .clickable { uiEventHandler.recordButtonClicked() }
                ) {
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        lineHeight = TextUnit(28F, TextUnitType.Sp),
                        fontSize = TextUnit(18F, TextUnitType.Sp),
                        color = ColorPalette.getTextColor()
                    )
                }
            }

            if (layout == "DeleteSaveOrPlay") {
                scrolling()

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight()
                            .background(ColorPalette.getSoftRedColor())
                            .clickable {
                                uiEventHandler.resetButtonClicked()
                            }
                    ) {
                        Image(
                            painterResource(
                                Res.drawable.delete_forever_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                            null,
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight()
                            .background(ColorPalette.getSoftBlueColor())
                            .clickable { uiEventHandler.saveButtonClicked() }
                    ) {
                        Image(
                            painterResource(
                                Res.drawable.download_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                            null,
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight()
                            .background(ColorPalette.getSoftGreenColor())
                            .clickable { uiEventHandler.playButtonClicked() }
                    ) {
                        // Play-Pause
                        val playbackState = modelData.playbackState.collectAsState().value
                        val icon = if (playbackState) {
                            Res.drawable.pause_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
                        } else {
                            Res.drawable.play_arrow_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
                        }

                        Image(
                            painterResource(icon),
                            null,
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                        )
                    }
                }
            }


            if (layout == "Player") {
                scrolling()

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight()
                            .background(ColorPalette.getSoftYellowColor())
                            .clickable { uiEventHandler.resetButtonClicked() }
                    ) {
                        Image(
                            painterResource(
                                Res.drawable.close_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                            null,
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight()
                            .background(ColorPalette.getSoftGreenColor())
                            .clickable { uiEventHandler.playButtonClicked() }
                    ) {
                        // Play-Pause
                        val playbackState = modelData.playbackState.collectAsState().value
                        val icon = if (playbackState) {
                            Res.drawable.pause_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
                        } else {
                            Res.drawable.play_arrow_24dp_E3E3E3_FILL0_wght400_GRAD0_opsz24
                        }

                        Image(
                            painterResource(icon),
                            null,
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                        )
                    }
                }
            }
        }
    }


    @Composable
    fun scrolling() {

        // Scrolling
        val pointerPosition = modelData.pointerPosition.collectAsState().value
        var lastPointerPosition by remember { mutableStateOf(pointerPosition) }
        val screenWidth = getScreenSizeInPx().first.toFloat()
        val slowRewindAcceleration = 0.125F
        lastPointerPosition = pointerPosition

        // Scrolling state
        var isShowSlowScrolling by remember { mutableStateOf(false) }

        // Slow scrolling
        AnimatedVisibility(isShowSlowScrolling) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(ColorPalette.getBlockColor())
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { change, dragAmount ->
                                change.consume()
                                lastPointerPosition = (lastPointerPosition + ((dragAmount /
                                        screenWidth) * slowRewindAcceleration)).coerceIn(
                                    0F,
                                    1F
                                )
                                uiEventHandler.rewindCallback(lastPointerPosition)
                            }
                        )
                    }
            ) {
                for (i in 1..7) {

                    Box(
                        modifier = Modifier
                            .height(28.dp)
                            .width(5.dp)
                            .background(ColorPalette.getBlockHighlightColor())
                    )
                }
            }
        }

        // Default scrolling
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(ColorPalette.getBlockColor())
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { change, dragAmount ->
                            change.consume()
                            lastPointerPosition = (lastPointerPosition + (dragAmount /
                                    screenWidth)).coerceIn(0F, 1F)
                            uiEventHandler.rewindCallback(lastPointerPosition)
                        }
                    )
                }
                .clickable(
                    onClick = {
                        // Switch slow scrolling
                        isShowSlowScrolling = !isShowSlowScrolling
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(
                        Res.drawable.arrow_back_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
                    ),
                    null,
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                        .alpha(0.32F)
                )

                Text(
                    text = "Scroll",
                    color = ColorPalette.getTextColor(),
                    modifier = Modifier
                        .alpha(0.5F)
                )

                Image(
                    painterResource(
                        Res.drawable.arrow_forward_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
                    ),
                    null,
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                        .alpha(0.32F)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                //.background(Color.Magenta)
            ) {
                var offset = pointerPosition
                if (offset > 1F) {
                    offset = 1F
                }
                if (offset < 0F) {
                    offset = 0F
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.00025F + offset)
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(10.dp)
                        .background(Color.Yellow)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.00025F - offset)
                )
            }
        }
    }
}
