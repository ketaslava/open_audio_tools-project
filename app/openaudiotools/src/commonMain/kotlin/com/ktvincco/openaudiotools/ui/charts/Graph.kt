package com.ktvincco.openaudiotools.ui.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.map
import openaudiotools.app.openaudiotools.generated.resources.Res
import openaudiotools.app.openaudiotools.generated.resources.arrow_back_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openaudiotools.app.openaudiotools.generated.resources.arrow_forward_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import org.jetbrains.compose.resources.painterResource
import kotlin.math.abs
import kotlin.math.roundToInt


class GraphZone(
    private val minLabel: Float,
    private val maxLabel: Float,
    private val color: Color
) {
    fun getMinLabel(): Float {
        return minLabel
    }

    fun getMaxLabel(): Float {
        return maxLabel
    }

    fun getColor(): Color {
        return color
    }
}


class Graph {


    @Composable
    fun draw(
        data: FloatArray,
        isScaleDataByYLabels: Boolean = false,
        yLabelMin: Float = 0F,
        yLabelMax: Float = 1F,
        xLabelMin: Float = 0F,
        xLabelMax: Float = 1F,
        horizontalLinesCount: Int = 10,
        graphZones: List<GraphZone> = listOf(),
        pointerPosition: Float = -1F,
        isEnableAutoScroll: Boolean = false,
        autoScrollXWindowSize: Float = 1F,
        modifier: Modifier = Modifier
    ) {

        // Settings

        // Sensitivity
        val gestureZoomAcceleration = 0.002F

        // Colors
        val backgroundColor = Color.hsv(0F, 0F, 0.15F)
        val lineColor = Color.White
        val pointsColor = Color.White
        val gridColor = Color.Gray
        val pointerColor = Color.Yellow
        val textColor = Color.Green

        // Text
        val fontSize = 10.sp

        // Optimization
        val graphXMaximalResolution = 0.01F
        val textXMaximalResolution = 0.1F

        // Variables

        // Scaling
        var scaleX by remember { mutableStateOf(1f) }
        var oldScaleX by remember { mutableStateOf(1f) }
        var centerOffset by remember { mutableStateOf(0f) }
        var offsetX by remember { mutableStateOf(0f) }

        // Gestures
        var isCurrentGestureCaptured by remember { mutableStateOf(false) }
        var isCurrentGestureHorizontal by remember { mutableStateOf(false) }

        // Process

        // Create components
        val textMeasurer = rememberTextMeasurer()

        // Check data -> reset graph when there is no data
        if (data.isEmpty() || data.size <= 1 || yLabelMin >= yLabelMax) {

            // Reset graph there is no data
            scaleX = 1F
            oldScaleX = 1F
            centerOffset = 0F
            offsetX = 0F
        }

        // Scale and Apply limits to the data
        if (isScaleDataByYLabels) {
            for (i in data.indices) {
                data[i] = data[i] * yLabelMax
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

                    // Detect horizontal gesture for drag
                    .pointerInput(Unit) {
                        awaitPointerEventScope {

                            // Wait for input
                            while (true) {
                                val event = awaitPointerEvent()
                                val pointer = event.changes.firstOrNull()

                                // On gesture
                                if (pointer != null && pointer.pressed) {

                                    // Calculate offset
                                    val delta = pointer.position - pointer.previousPosition

                                    // Capture gesture
                                    if (!isCurrentGestureCaptured && delta != Offset.Zero) {
                                        isCurrentGestureCaptured = true

                                        // Calculate is horizontal
                                        isCurrentGestureHorizontal = abs(delta.x) > abs(delta.y)
                                    }

                                    // Detect horizontal gesture
                                    if (isCurrentGestureCaptured && isCurrentGestureHorizontal) {

                                        // Drag
                                        offsetX += delta.x
                                        pointer.consume()
                                    }

                                } else {
                                    // Release gesture
                                    isCurrentGestureCaptured = false
                                }
                            }
                        }
                    }
            ) {
                // Calculate

                // Sizes
                val graphWidth = size.width
                val graphHeight = size.height
                centerOffset = graphWidth / 2

                // Auto scroll
                if (isEnableAutoScroll && autoScrollXWindowSize > 0F &&
                    (xLabelMax - xLabelMin) > autoScrollXWindowSize) {
                    // Apply auto scrolled position
                    scaleX = (xLabelMax - xLabelMin) / autoScrollXWindowSize
                    offsetX = -((graphWidth * scaleX) - graphWidth)
                }

                // Limits
                if (scaleX < 1F) {
                    scaleX = 1F
                }

                // Changes
                val change = scaleX / oldScaleX
                oldScaleX = scaleX
                offsetX = ((offsetX - centerOffset) * change) + centerOffset

                // Steps
                val xStep = graphWidth / (data.size - 1) * scaleX
                val yScale = graphHeight / (yLabelMax - yLabelMin)

                // Limit offset
                val maxOffset = 0f
                var minOffset = -(xStep * (data.size - 1) - graphWidth)
                if (minOffset > 0F) {
                    minOffset = 0F
                }
                offsetX = offsetX.coerceIn(minOffset, maxOffset)

                // Draw

                // Background
                drawRect(
                    color = backgroundColor,
                    topLeft = Offset(0f, 0F),
                    size = Size(width = graphWidth, height = graphHeight)
                )

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

                // Draw Zones (main, top, bottom)
                for (zone in graphZones) {
                    val minH = map(zone.getMinLabel(), yLabelMin,
                        yLabelMax, graphHeight, 0F)
                    val maxH = map(zone.getMaxLabel(), yLabelMin,
                        yLabelMax, graphHeight, 0F)
                    drawRect(
                        color = zone.getColor(),
                        topLeft = Offset(0f, minH),
                        size = Size(width = graphWidth, height = maxH - minH)
                    )
                }

                // Draw horizontal elements
                val yStepValue = (yLabelMax - yLabelMin) / horizontalLinesCount
                val yStepPosition = graphHeight / horizontalLinesCount

                for (i in 0..horizontalLinesCount) {
                    val yValue = yLabelMin + i * yStepValue
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


                // Draw vertical elements
                // Optimization graph
                val drawRes = graphWidth * graphXMaximalResolution
                var lastDrawX = 0F
                var allX = 0F
                var allY = 0F
                var count = 0
                var lastX = 0F
                var lastY = 0F
                // Optimization text
                val textMaximalDrawRes = graphWidth * textXMaximalResolution
                var textLastDrawX = 0F

                for (i in data.indices) {

                    // Get value
                    val v = data[i]
                    // Check range
                    if (v < yLabelMin || v > yLabelMax) {
                        continue
                    }

                    // Calculate base
                    var x = i * xStep + offsetX
                    var y = graphHeight - (v - yLabelMin) * yScale

                    // Apply limit (no draw beyond graph)
                    if (y.toInt() !in 0..graphHeight.toInt()) {
                        // Skip
                        continue
                    }

                    // Combine average value of points in graph
                    allX += x
                    allY += y
                    count += 1

                    // Process resolution skip
                    if (x - lastDrawX < drawRes && lastDrawX > 0F) {
                        continue
                    }
                    lastDrawX = x

                    // Use and reset average value
                    x = allX / count.toFloat()
                    y = allY / count.toFloat()
                    allX = 0F
                    allY = 0F
                    count = 0

                    // Draw text
                    if (x - textLastDrawX > textMaximalDrawRes) {
                        val xValue = (xLabelMin + (xLabelMax - xLabelMin) *
                                (i.toFloat() / data.size.toFloat())) // Interpolate X axis value
                        val text = ((xValue * 10F).toInt().toFloat() / 10F).toString() // 0.0F
                        val textLayout = textMeasurer.measure(
                            text, style = TextStyle(fontSize = fontSize))
                        val textY = graphHeight - (textLayout.size.height * 1.25F)

                        if (x in 0F..graphWidth && textY in 0F..graphHeight) {
                            drawText(
                                text = text,
                                textMeasurer = textMeasurer,
                                topLeft = Offset(
                                    x = x,
                                    y = textY
                                ),
                                style = TextStyle(
                                    fontSize = fontSize,
                                    fontWeight = FontWeight.Bold,
                                    color = textColor
                                )
                            )
                        }
                        textLastDrawX = x
                    }

                    // Draw point
                    if (x in 0F..graphWidth) {
                        drawCircle(
                            color = pointsColor,
                            radius = 3F,
                            center = Offset(x, y)
                        )
                    }

                    // Draw line
                    if (lastX in 0f..graphWidth || x in 0f..graphWidth){
                        drawLine(
                            color = lineColor,
                            start = Offset(lastX, lastY),
                            end = Offset(x, y),
                            strokeWidth = 2f
                        )
                    }

                    // Assign state
                    lastX = x
                    lastY = y
                }


                // Pointer
                if (pointerPosition in 0F..1F) {
                    val pointerX = pointerPosition * graphWidth * scaleX + offsetX
                    drawLine(
                        color = pointerColor,
                        start = Offset(pointerX, 0F),
                        end = Offset(pointerX, graphHeight),
                        strokeWidth = 2f
                    )
                }
            }

            // Zoom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(ColorPalette.getBlockColor())
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { change, dragAmount ->
                                // Zoom
                                var zoomAdv = dragAmount
                                zoomAdv = ((zoomAdv - 1F) * gestureZoomAcceleration) + 1F
                                scaleX *= zoomAdv
                                // Consume changes
                                change.consume()
                            }
                        )
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Image(
                        painterResource(
                            Res.drawable.arrow_back_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
                        ),
                        null,
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .alpha(0.32F)
                    )

                    Text(
                        text = "Zoom",
                        color = ColorPalette.getTextColor(),
                        modifier = Modifier
                            .alpha(0.5F)
                    )

                    Image(
                        painterResource(
                            Res.drawable.arrow_forward_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
                        ),
                        null,
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .alpha(0.32F)
                    )
                }
            }
        }
    }
}