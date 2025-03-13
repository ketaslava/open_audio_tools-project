package com.ktvincco.openvoiceanalyzer.ui.pages

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openvoiceanalyzer.ColorPalette
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler
import com.ktvincco.openvoiceanalyzer.ui.BaseComponents
import openvoiceanalyzer.composeapp.generated.resources.Res
import openvoiceanalyzer.composeapp.generated.resources.data_thresholding_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openvoiceanalyzer.composeapp.generated.resources.delete_forever_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openvoiceanalyzer.composeapp.generated.resources.save_as_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import org.jetbrains.compose.resources.painterResource


class Recordings (private val modelData: ModelData, private val uiEventHandler: UiEventHandler) {


    @Composable
    fun draw() {

        val recordingFileList = modelData.recordingFileList.collectAsState().value
        var currentSelectionId by remember { mutableStateOf(-1) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(state = ScrollState(0))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .verticalScroll(state = ScrollState(0))
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Recordings",
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
                    itemsIndexed(recordingFileList) { index, fileName ->

                        BaseComponents().HorizontalDivider(
                            color = ColorPalette.getMarkupColor(), thickness = 1.dp)

                        menuItem(index, fileName, index == currentSelectionId) { id ->
                            currentSelectionId = id
                        }

                        if (recordingFileList.isNotEmpty() &&
                                index == recordingFileList.size - 1) {
                            BaseComponents().HorizontalDivider(
                                color = ColorPalette.getMarkupColor(), thickness = 1.dp)
                        }
                    }
                }
            }
            if (currentSelectionId in recordingFileList.indices) {
                actionBar(recordingFileList[currentSelectionId]) {
                    currentSelectionId = -1
                }
            }
        }
    }


    @Composable
    fun menuItem(id: Int, text: String, isSelected: Boolean, callback: (id: Int) -> Unit) {

        val color = if(isSelected) { ColorPalette.getSelectionColor() } else { Color.Transparent }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color)
                .clickable { callback.invoke(id) }
        ) {
            Text(
                text = text,
                color = ColorPalette.getTextColor()
            )
        }
    }


    @Composable
    fun actionBar(selectedFileName: String, resetSelection: () -> Unit) {
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
                    .clickable { // Open popup before deletion
                        modelData.openPopup("Warning", "Delete $selectedFileName ?") {
                            exitButtonType ->
                            if (exitButtonType == "Ok") {
                                uiEventHandler.deleteRecordingFile(selectedFileName)
                                resetSelection.invoke()
                            }
                        }
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
                    .background(ColorPalette.getSoftYellowColor())
                    .clickable {
                        modelData.openPopupWithTextInput(
                            "Rename") { exitButtonType, inputText ->
                            if (exitButtonType == "Ok") {
                                uiEventHandler.renameRecordingFile(selectedFileName, inputText)
                                resetSelection()
                            }
                        }
                    }
            ) {
                Image(
                    painterResource(
                        Res.drawable.save_as_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
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
                    .clickable {
                        uiEventHandler.loadRecordingButtonClicked(selectedFileName)
                    }
            ) {
                Image(
                    painterResource(
                        Res.drawable.data_thresholding_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                    null,
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }
        }
    }
}