package com.ktvincco.openaudiotools.ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.TextForReading
import com.ktvincco.openaudiotools.Texts
import com.ktvincco.openaudiotools.ui.basics.BaseComponents
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.presentation.UiEventHandler
import com.ktvincco.openaudiotools.ui.components.RecordingControl
import openaudiotools.app.openaudiotools.generated.resources.Res
import openaudiotools.app.openaudiotools.generated.resources.menu_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import org.jetbrains.compose.resources.painterResource


class Reading (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {
    @Composable
    fun draw() {

        var isOpenTextSelectionMenu by remember { mutableStateOf(false) }
        var currentTextId by remember { mutableStateOf(0) }
        val texts = Texts.getTextsForReading()

        val scrollState = rememberScrollState()
        val textScrollState = rememberScrollState()

        // Content except Menu
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            // Content except Recording Control
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                // Content
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(state = scrollState)
                ) {

                    // Top bar (open text selection button)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(ColorPalette.getBlockColor())
                            .clickable { isOpenTextSelectionMenu = !isOpenTextSelectionMenu}
                            .padding(horizontal = 30.dp)
                    ) {
                        Text(
                            text = texts[currentTextId].getHeadline(),
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

                    // Text
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .height(2048.dp)
                            .fillMaxWidth()
                            .padding(30.dp)
                            .verticalScroll(state = textScrollState)
                    ) {
                        Text(
                            text = texts[currentTextId].getText(),
                            color = ColorPalette.getTextColor(),
                            fontSize = 16.sp,
                            lineHeight = 28.sp,
                        )
                    }
                }
                RecordingControl(modelData, uiEventHandler).draw()
            }
            AnimatedVisibility(isOpenTextSelectionMenu) {
                textSelectionMenu (texts) { selectedTextId ->
                    currentTextId = selectedTextId
                    isOpenTextSelectionMenu = false
                }
            }
        }
    }



    @Composable
    fun textSelectionMenu(textsForReading: Array<TextForReading>,
                          onTextSelection: (selectedTextId: Int) -> Unit) {
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
                text = "Select the text",
                color = ColorPalette.getTextColor(),
                fontSize = 24.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                itemsIndexed(textsForReading) { index, textForReading ->

                    BaseComponents().HorizontalDivider(
                        color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                    menuItem(textForReading.getHeadline()) {
                        onTextSelection.invoke(index)
                    }

                    if (textsForReading.isNotEmpty() &&
                        index == textsForReading.size - 1) {
                        BaseComponents().HorizontalDivider(
                            color = ColorPalette.getMarkupColor(), thickness = 1.dp)
                    }
                }
            }
        }
    }


    @Composable
    fun menuItem(text: String, callback: () -> Unit) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .clickable { callback.invoke() }
        ) {
            Text(
                text = text,
                color = ColorPalette.getTextColor(),
                style = MaterialTheme.typography.body1
            )
        }
    }
}