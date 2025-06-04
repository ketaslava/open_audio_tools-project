package com.ktvincco.openaudiotools.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.presentation.BasicModelData
import com.ktvincco.openaudiotools.presentation.BasicUiEventHandler


class UserSettingsPage (
    private val basicModelData: BasicModelData,
    private val basicUiEventHandler: BasicUiEventHandler
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