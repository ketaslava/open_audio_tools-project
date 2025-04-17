package com.ktvincco.openaudiotools.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.presentation.ModelData
import com.ktvincco.openaudiotools.presentation.UiEventHandler
import openaudiotools.common.openaudiotools.generated.resources.Res
import openaudiotools.common.openaudiotools.generated.resources.triangle_pointer
import org.jetbrains.compose.resources.painterResource
import kotlin.math.abs


private fun map(source: Float, minSource: Float, maxSource: Float,
                minTarget: Float, maxTarget: Float): Float {
    val s = (source - minSource) / (maxSource - minSource)
    return minTarget + (maxTarget - minTarget) * s
}


class MiniDisplay (
    private val modelData: ModelData,
    private val uiEventHandler: UiEventHandler
) {

    @Composable
    fun miniDisplay(
        text: String,
        value: Float,
        isEnableNegativeValues: Boolean = false,
        normalRangeMin: Float = 0F,
        normalRangeMax: Float = 1F,
        isEnableDeadZoneLow: Boolean = false,
        isEnableDeadZoneHigh: Boolean = false,
        modifier: Modifier
    ) {

        // Current value which is filtered
        var currentValue by remember { mutableStateOf(0F) }
        if (value >= 0F || (isEnableNegativeValues && value > -abs(normalRangeMin) * 2F)) {
            currentValue = value
        }

        // Smooth
        val animatedValue by animateFloatAsState(
            targetValue = currentValue,
            animationSpec = tween(durationMillis = 333)
        )

        // Ded zones draw
        var deadZoneLowColor = ColorPalette.getSoftRedColor()
        var centerZoneColor = ColorPalette.getSoftGreenColor()
        var deadZoneHighColor = ColorPalette.getSoftRedColor()
        if (!isEnableDeadZoneLow and !isEnableDeadZoneHigh) {
            deadZoneLowColor = ColorPalette.getSoftGrayColor()
            centerZoneColor = ColorPalette.getSoftGrayColor()
            deadZoneHighColor = ColorPalette.getSoftGrayColor()
        }
        if (isEnableDeadZoneLow and !isEnableDeadZoneHigh) {
            deadZoneHighColor = ColorPalette.getSoftGreenColor()
        }
        if (!isEnableDeadZoneLow and isEnableDeadZoneHigh) {
            deadZoneLowColor = ColorPalette.getSoftGreenColor()
        }

        // Borderline color
        var borderlineColor = ColorPalette.getMarkupColor()
        if (isEnableDeadZoneLow && animatedValue < normalRangeMin) {
            borderlineColor = Color.Red
        }
        if (isEnableDeadZoneHigh && animatedValue > normalRangeMax) {
            borderlineColor = Color.Red
        }

        // Value for display
        val minTarget = if (isEnableDeadZoneLow) 0.2F else 0F
        val maxTarget = if (isEnableDeadZoneHigh) 0.8F else 1F
        val displayValue = map(animatedValue, normalRangeMin, normalRangeMax,
            minTarget, maxTarget).coerceIn(0F, 1F)

        // Draw
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .height(90.dp)
                .border(2.dp, borderlineColor, shape = RoundedCornerShape(6.dp))
                .padding(16.dp)
        ) {
            val valueText = if (normalRangeMax < 10) { " ${(currentValue * 10).toInt() / 10F}"
            } else { " ${currentValue.toInt()}" }

            Text(
                text = text + valueText,
                color = ColorPalette.getTextColor(),
                fontSize = TextUnit(15F, TextUnitType.Sp),
                lineHeight = TextUnit(15F, TextUnitType.Sp),
                modifier = Modifier
                    .align(Alignment.Start)
            )

            Spacer(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxHeight()
            )

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            //.background(Color.Red)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .weight(1F)
                                .background(deadZoneLowColor)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .weight(3F)
                                .background(centerZoneColor)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .weight(1F)
                                .background(deadZoneHighColor)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                ) {
                    var offset = displayValue
                    if (offset > 1F) { offset = 1F }
                    if (offset < 0F) { offset = 0F }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.01F + offset)
                    )
                    Image(
                        painterResource(
                            Res.drawable.triangle_pointer),
                        null,
                        modifier = Modifier
                            .fillMaxHeight()
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.01F - offset)
                    )
                }
            }
        }
    }
}