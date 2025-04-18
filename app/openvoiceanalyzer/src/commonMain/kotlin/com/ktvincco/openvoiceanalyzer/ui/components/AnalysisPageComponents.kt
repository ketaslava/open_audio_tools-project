package com.ktvincco.openvoiceanalyzer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.Texts
import com.ktvincco.openaudiotools.ui.charts.MiniDisplay
import com.ktvincco.openvoiceanalyzer.presentation.ModelData
import com.ktvincco.openvoiceanalyzer.presentation.UiEventHandler
import openaudiotools.app.openvoiceanalyzer.generated.resources.Res
import openaudiotools.app.openvoiceanalyzer.generated.resources.developer_guide_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import org.jetbrains.compose.resources.painterResource


@Composable
fun graphNameText(modelData: ModelData, parameterId: String, nameAddition: String = "") {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    modelData.setHelpMenuState(true)
                    modelData.setHelpMenuParameterId(parameterId)
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Text(
            text = Texts.getParameterDescription(parameterId).getParameterName() + nameAddition,
            color = ColorPalette.getTextColor(),
            fontSize = 22.sp,
            lineHeight = 22.sp
        )

        Image(
            painterResource(
                Res.drawable.developer_guide_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
            ),
            null,
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .alpha(0.75F)
        )
    }
}


@Composable
fun graphColorDescriptionBlock(color: Color, text: String) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
                .background(color)
        )

        Spacer(modifier = Modifier.width(24.dp))

        Text(
            text = text,
            color = ColorPalette.getTextColor(),
            fontSize = 16.sp,
            lineHeight = 24.sp,
            modifier = Modifier
                .height(24.dp)
        )
    }
}


@Composable
fun miniDisplayBox(
    modelData: ModelData,
    uiEventHandler: UiEventHandler,
    parameterId: String,
    nameAddition: String = "",
    isEnableNegativeValues: Boolean = false,
    normalRangeMin: Float = 0F,
    normalRangeMax: Float = 1F,
    isEnableDeadZoneLow: Boolean = false,
    isEnableDeadZoneHigh: Boolean = false,
    isUseEmptyPlaceInsteadOfSecondDisplay: Boolean = false,
    d2ParameterId: String = "",
    d2NameAddition: String = "",
    d2IsEnableNegativeValues: Boolean = false,
    d2NormalRangeMin: Float = 0F,
    d2NormalRangeMax: Float = 1F,
    d2IsEnableDeadZoneLow: Boolean = false,
    d2IsEnableDeadZoneHigh: Boolean = false,
) {
    val display1Name = Texts.getParameterDescription(parameterId).getParameterName() + nameAddition
    val display2Name = Texts.getParameterDescription(d2ParameterId).getParameterName() + d2NameAddition

    val display1Value = modelData.getDisplayData(parameterId)
    val display2Value = modelData.getDisplayData(d2ParameterId)

    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        // Draw display 1
        MiniDisplay(modelData, uiEventHandler).miniDisplay(
            display1Name, display1Value, isEnableNegativeValues, normalRangeMin,
            normalRangeMax, isEnableDeadZoneLow, isEnableDeadZoneHigh, Modifier.weight(1F))

        if (d2ParameterId != "") {

            Spacer(Modifier.width(24.dp))

            // Draw display 2
            MiniDisplay(modelData, uiEventHandler).miniDisplay(
                display2Name, display2Value, d2IsEnableNegativeValues,
                d2NormalRangeMin, d2NormalRangeMax, d2IsEnableDeadZoneLow,
                d2IsEnableDeadZoneHigh, Modifier.weight(1F)
            )

        } else if(isUseEmptyPlaceInsteadOfSecondDisplay) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            )
        }
    }
}