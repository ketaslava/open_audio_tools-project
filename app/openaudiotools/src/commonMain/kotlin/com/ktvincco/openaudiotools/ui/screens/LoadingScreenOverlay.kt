package com.ktvincco.openaudiotools.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.presentation.UiEventHandler


class LoadingScreenOverlay (
    private val modelData: ModelData, private val uiEventHandler: UiEventHandler) {

    @Composable
    fun draw() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.33F))
                .clickable(
                    onClick = {},
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        ) {
            Text(
                text = "Loading...",
                color = ColorPalette.getTextColor()
            )
        }
    }

}