package com.ktvincco.openaudiotools.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ktvincco.openaudiotools.presentation.ModelData


class PageWithBottomControls (
    private val modelData: ModelData
) {

    @Composable
    fun draw(content: @Composable ColumnScope.() -> Unit,
             isEnableRecordingControl: Boolean = true) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier =  Modifier
                .fillMaxWidth()
        ) {

            // Scroll
            val scrollState = rememberScrollState()

            // Main column
            Column(
                content = content,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(state = scrollState)
                    .fillMaxWidth()
            )

            if(isEnableRecordingControl) {
                RecordingControl(modelData).draw()
            }
        }
    }
}