package com.ktvincco.openvoiceanalyzer.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.amplitudeToDecibels
import com.ktvincco.openaudiotools.getPlasmaColor
import com.ktvincco.openaudiotools.map
import com.ktvincco.openaudiotools.normalizeDecibels
import kotlin.math.roundToInt


class Spectrum {

    @Composable
    fun Spectrum(
        inputData: Array<FloatArray>,
        isNormalizeValues: Boolean = false,
        isUseLogScale: Boolean = false,
        multiplyValue: Float = 1F,
        xLabelMin: Float = 0F,
        xLabelMax: Float = 1F,
        horizontalLinesCount: Int = 8,
        textBlocksCount: Int = 16,
        graphZones: List<GraphZone> = listOf(),
        pointerPosition: Float = -1F,
        isUpdateFromLastData: Boolean = false,
        modifier: Modifier = Modifier
    ) {

        // Settings

        // Colors
        val backgroundColor = Color.hsv(0F, 0F, 0.15F)
        val gridColor = Color.Gray
        val textColor = Color.Green
        val maximumsColor = Color.Red

        // Text
        val fontSize = 10.sp

        // Optimization
        val graphXMaximalResolution = 0.005F

        // Variables

        // Maximums data
        var maximumsData by remember { mutableStateOf(floatArrayOf()) }

        // Create components
        val textMeasurer = rememberTextMeasurer()

        // Get data
        var data = floatArrayOf()

        println(pointerPosition)
        println(isUpdateFromLastData)

        if (inputData.isNotEmpty() && inputData.size > 1) {
            if (isUpdateFromLastData) {
                // Update from last data
                data = inputData.last()
            } else {
                // Update from pointer position
                val dataFrame = (inputData.size.toFloat() * pointerPosition).toInt()
                data = inputData[dataFrame.coerceIn(inputData.indices)]
            }
        }

        Column(
            Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Draw canvas
            Canvas(
                modifier = modifier
            ) {
                // Sizes
                val graphWidth = size.width
                val graphHeight = size.height

                // Maximums
                if (maximumsData.size != data.size) {
                    maximumsData = data.copyOf()
                }
                if (data.size > 1 && maximumsData.size == data.size) {
                    for (i in maximumsData.indices) {
                        if (maximumsData[i] < data[i]) {
                            maximumsData[i] = data[i]
                        }
                    }
                }

                // Background
                drawRect(
                    color = backgroundColor,
                    topLeft = Offset(0f, 0F),
                    size = Size(width = graphWidth, height = graphHeight)
                )

                // Draw Zones (main, top, bottom)
                for (zone in graphZones) {
                    val minH = map(zone.getMinLabel(), xLabelMin, xLabelMax, graphWidth, 0F)
                    val maxH = map(zone.getMaxLabel(), xLabelMin, xLabelMax, graphWidth, 0F)
                    drawRect(
                        color = zone.getColor(),
                        topLeft = Offset(minH, 0f),
                        size = Size(width = maxH - minH, height = graphHeight)
                    )
                }

                // ===== Draw data ===== //
                // Optimization graph
                val xStep = graphWidth / data.size
                var maxValue = 1F
                if (data.isNotEmpty()) {
                    maxValue = data.max()
                }
                val drawRes = graphWidth * graphXMaximalResolution
                var lastDrawX = 0F
                var combinedValue = 0F
                var combinedMaximumValue = 0F
                var xCount = 0

                for (i in data.indices) {

                    // Optimization

                    // Calculate base
                    val x = i * xStep

                    // Combine average value
                    combinedValue += data[i]
                    combinedMaximumValue += maximumsData[i]
                    xCount += 1

                    // Process resolution skip
                    if (x - lastDrawX < drawRes) {
                        continue
                    }

                    // Draw maximums

                    // Calculate value
                    var maximumValue = combinedMaximumValue / xCount.toFloat()
                    maximumValue *= multiplyValue
                    if (isNormalizeValues) { maximumValue /= maxValue }
                    maximumValue = maximumValue.coerceIn(0F, 1F)
                    if (maximumValue.isNaN()) { maximumValue = 0F }

                    // Transform value by Log
                    if (isUseLogScale) {
                        maximumValue = normalizeDecibels(amplitudeToDecibels(maximumValue))
                    }

                    // Draw
                    val maximumY = graphHeight - (graphHeight * maximumValue)
                    drawRect(
                        color = maximumsColor,
                        topLeft = Offset(x, maximumY),
                        size = Size(width = x - lastDrawX, height = graphHeight - maximumY)
                    )

                    // Draw value

                    // Calculate value
                    var value = combinedValue / xCount.toFloat()
                    value *= multiplyValue
                    if (isNormalizeValues) { value /= maxValue }
                    value = value.coerceIn(0F, 1F)
                    if (value.isNaN()) { value = 0F }

                    // Transform value by Log
                    if (isUseLogScale) {
                        value = normalizeDecibels(amplitudeToDecibels(value))
                    }

                    // Calculate color
                    val color = getPlasmaColor(value)

                    // Draw
                    val y = graphHeight - (graphHeight * value)
                    drawRect(
                        color = color,
                        topLeft = Offset(x, y),
                        size = Size(width = x - lastDrawX, height = graphHeight - y)
                    )

                    // Reset

                    // Reset optimization data
                    combinedValue = 0F
                    combinedMaximumValue = 0F
                    xCount = 0
                    lastDrawX = x
                }

                // ===== Draw Text ===== //
                val xStepPosition = graphWidth / textBlocksCount

                for (i in 1..textBlocksCount) {
                    val x = i * xStepPosition

                    // Lines
                    drawLine(
                        color = gridColor,
                        start = Offset(x, 0F),
                        end = Offset(x, graphHeight),
                        strokeWidth = 1f
                    )

                    // Text
                    val text = (xLabelMin + (xLabelMax - xLabelMin) *
                            (i.toFloat() / textBlocksCount.toFloat())).toInt().toString()
                    val textLayout = textMeasurer.measure(
                        text, style = TextStyle(fontSize = fontSize))
                    val textX = x + (textLayout.size.height * 0.25F)

                    if (textX > 0 && textX < graphWidth) {
                        // Bottom
                        drawText(
                            text = text,
                            textMeasurer = textMeasurer,
                            topLeft = Offset(
                                x = textX,
                                y = graphHeight - (textLayout.size.height * 1.25F)
                            ),
                            style = TextStyle(
                                fontSize = fontSize,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                        )
                        // Top
                        drawText(
                            text = text,
                            textMeasurer = textMeasurer,
                            topLeft = Offset(
                                x = textX,
                                y = textLayout.size.height * 0.25F
                            ),
                            style = TextStyle(
                                fontSize = fontSize,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                        )
                    }
                }

                // Borderlines
                drawLine(
                    color = Color.White,
                    start = Offset(0F, 2F),
                    end = Offset(graphWidth, 2F),
                    strokeWidth = 4f
                )
                drawLine(
                    color = Color.White,
                    start = Offset(0F, graphHeight - 2F),
                    end = Offset(graphWidth, graphHeight - 2F),
                    strokeWidth = 4f
                )

                // ===== Draw horizontal elements ===== //
                val yStepValue = 1F / horizontalLinesCount
                val yStepPosition = graphHeight / horizontalLinesCount

                for (i in 0..horizontalLinesCount) {
                    val yValue = i * yStepValue
                    val yPosition = graphHeight - i * yStepPosition

                    // Lines
                    drawLine(
                        color = gridColor,
                        start = Offset(0f, yPosition),
                        end = Offset(graphWidth, yPosition),
                        strokeWidth = 1f
                    )

                    // Text
                    val text = ((yValue * 10).roundToInt().toFloat() / 10F).toString()
                    val textLayout = textMeasurer.measure(
                        text, style = TextStyle(fontSize = fontSize))
                    val textX = textLayout.size.height.toFloat() * 0.5F
                    val textY = yPosition - textLayout.size.height

                    if (textX > 0 && textX < graphWidth && textY > 0 && textY < graphHeight) {
                        drawText(
                            text = text,
                            textMeasurer = textMeasurer,
                            topLeft = Offset(
                                x = textX,
                                y = textY
                            ),
                            style = TextStyle(
                                fontSize = fontSize,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                        )
                    }
                }
            }

            // Zoom
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(ColorPalette.getBlockColor())
                    .clickable {
                        // Reset maximums
                        maximumsData = floatArrayOf()
                    }
            ) {
                Text(
                    text = "Reset",
                    color = ColorPalette.getTextColor(),
                    modifier = Modifier
                        .alpha(0.5F)
                )
            }
        }
    }
}