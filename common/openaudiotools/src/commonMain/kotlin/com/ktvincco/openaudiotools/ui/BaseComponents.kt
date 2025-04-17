package com.ktvincco.openaudiotools.ui


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ktvincco.openaudiotools.ColorPalette
import openaudiotools.common.openaudiotools.generated.resources.Res
import openaudiotools.common.openaudiotools.generated.resources.check_box_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openaudiotools.common.openaudiotools.generated.resources.check_box_outline_blank_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import org.jetbrains.compose.resources.painterResource


class BaseComponents {


    @Composable
    fun Checkbox(text: String, modifier: Modifier, callback: (state: Boolean) -> Unit) {

        var isCheckboxChecked by remember { mutableStateOf(false) }

        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    // Switch and emit callback
                    isCheckboxChecked = !isCheckboxChecked
                    callback.invoke(isCheckboxChecked)
                }
        ) {
            var icon = Res.drawable.check_box_outline_blank_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
            if (isCheckboxChecked) {
                icon = Res.drawable.check_box_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
            }
            Image(
                painterResource(icon),
                null,
                modifier = Modifier
                    .width(52.dp)
                    .height(52.dp)
                    //.background(Color.Magenta)
                    .clickable {
                        // Switch and emit callback
                        isCheckboxChecked = !isCheckboxChecked
                        callback.invoke(isCheckboxChecked)
                    }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = text,
                color = ColorPalette.getTextColor()
            )
        }
    }


    @Composable
    fun HorizontalDivider(
        modifier: Modifier = Modifier,
        thickness: Dp = DividerDefaults.Thickness,
        color: Color = DividerDefaults.color,
    ) = Canvas(modifier.fillMaxWidth().height(thickness)) {
        drawLine(
            color = color,
            strokeWidth = thickness.toPx(),
            start = Offset(0f, thickness.toPx() / 2),
            end = Offset(size.width, thickness.toPx() / 2),
        )
    }

    @Composable
    fun VerticalDivider(
        modifier: Modifier = Modifier,
        thickness: Dp = DividerDefaults.Thickness,
        color: Color = DividerDefaults.color,
    ) = Canvas(modifier.fillMaxHeight().width(thickness)) {
        drawLine(
            color = color,
            strokeWidth = thickness.toPx(),
            start = Offset(thickness.toPx() / 2, 0f),
            end = Offset(thickness.toPx() / 2, size.height),
        )
    }

    object DividerDefaults {
        val Thickness: Dp = 1.dp
        val color: Color = Color.White
    }
}