package com.ktvincco.openaudiotools.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.presentation.UiEventHandler


class UserSettingsPage (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    @Composable
    fun draw() {
        Text(
            text = "Settings",
            color = ColorPalette.getTextColor(),
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.h5
        )
    }

}