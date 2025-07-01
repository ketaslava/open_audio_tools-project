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
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktvincco.openaudiotools.ColorPalette
import com.ktvincco.openaudiotools.DynamicText
import com.ktvincco.openaudiotools.presentation.ModelData
import openaudiotools.app.openaudiotools.generated.resources.Res
import openaudiotools.app.openaudiotools.generated.resources.arrow_back_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import openaudiotools.app.openaudiotools.generated.resources.arrow_forward_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import org.jetbrains.compose.resources.painterResource
import kotlin.math.abs
import kotlin.math.log2


class NoteGraph {


    private fun linearToNote(pitch: Float): Float {
        // A4 = 440 Hz, MIDI at 69
        val a4Frequency = 440.0
        val a4MidiNumber = 69.0

        // Pitch to MIDI
        return (12 * log2(pitch / a4Frequency) + a4MidiNumber).toFloat()
    }


    private fun getNoteName(index: Int): String {

        // Make a list
        val noteNames = listOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")

        // Calculate
        val noteIndex = index % 12
        val octave = index / 12 - 1
        return "${noteNames[noteIndex]}$octave"
    }


    @Composable
    fun draw(
        data: FloatArray,
        modelData: ModelData,
        xLabelMin: Float = 0F,
        xLabelMax: Float = 1F,
        pointerPosition: Float = -1F,
        isEnableAutoScroll: Boolean = false,
        autoScrollXWindowSize: Float = 1F,
        modifier: Modifier = Modifier
    ) {

        // Settings

        // Base
        val horizontalElementHeightProportion = 0.04F
        val levelLabelWidthProportion = 0.06F
        val notesCount = 86
        val autoOffsetWindowDistance = horizontalElementHeightProportion * 4

        // Sensitivity
        val gestureZoomAcceleration = 0.002F

        // Colors
        val backgroundColor = Color.hsv(0F, 0F, 0.15F)
        val lineColor = Color.White
        val pointsColor = Color.White
        val gridColor = Color.Gray
        val pointerColor = Color.Yellow
        val textColor = Color.White

        // Text
        val fontSize = 10.sp

        // Optimization
        val graphXMaximalResolution = 0.001F
        val textXMaximalResolution = 0.1F

        // Variables

        // Scaling
        var scaleX by remember { mutableStateOf(1f) }
        var oldScaleX by remember { mutableStateOf(1f) }
        var centerOffset by remember { mutableStateOf(0f) }
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        // Gestures
        var isCurrentGestureCaptured by remember { mutableStateOf(false) }
        var isCurrentGestureHorizontal by remember { mutableStateOf(false) }

        // Auto Offset and note selection
        var lastNoteIndex by remember { mutableStateOf(0) }
        var autoOffsetNoteIndex by remember { mutableStateOf(0) }
        var lastCanvasHeight by remember { mutableStateOf(0F) }

        // Scroll
        var isDragging by remember { mutableStateOf(false) }
        var inertiaY by remember { mutableStateOf(0f) }
        var lastUpdateTime by remember { mutableStateOf(0L) }
        val inertiaDrag = 1F
        val nowMs = System.currentTimeMillis()
        if (!isDragging && inertiaY != 0F) {
            val deltaTime = (nowMs - lastUpdateTime).toFloat()
            val change = inertiaY * (inertiaDrag * (deltaTime / 1000F))
            inertiaY -= change
            offsetY += inertiaY

            if (offsetY < 0) {
                offsetY = 0F
                inertiaY = 0F
            }
            val maxOffset = (horizontalElementHeightProportion *
                    lastCanvasHeight * notesCount) - lastCanvasHeight
            if (offsetY > maxOffset) {
                offsetY = maxOffset
                inertiaY = 0F
            }
        }
        lastUpdateTime = nowMs

        // Process

        // Create components
        val textMeasurer = rememberTextMeasurer()
        val textLayoutResult = remember("|--|") {
            textMeasurer.measure("|--|", style = TextStyle(fontSize = fontSize)) }

        // Check data -> reset graph when there is no data
        if (data.isEmpty() || data.size <= 1) {

            // Reset graph there is no data
            scaleX = 1F
            oldScaleX = 1F
            centerOffset = 0F
            offsetX = 0F
            offsetY = 0F
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

                                    // Detect hold gesture
                                    if (!isCurrentGestureCaptured && delta == Offset.Zero) {

                                        // Stop drag
                                        inertiaY = 0F
                                        isDragging = true
                                    }

                                    // Detect horizontal gesture
                                    if (isCurrentGestureCaptured && isCurrentGestureHorizontal) {

                                        // Drag
                                        offsetX += delta.x
                                        pointer.consume()
                                    }

                                    // Detect vertical gesture
                                    if (isCurrentGestureCaptured && !isCurrentGestureHorizontal) {

                                        // Drag
                                        offsetY += delta.y

                                        // Check limits
                                        if (offsetY < 0) {
                                            offsetY = 0F
                                            continue
                                        }
                                        val maxOffset = (horizontalElementHeightProportion *
                                                lastCanvasHeight * notesCount) - lastCanvasHeight
                                        if (offsetY > maxOffset) {
                                            offsetY = maxOffset
                                            continue
                                        }

                                        // Set scroll state
                                        isDragging = true
                                        inertiaY = delta.y

                                        // Consume input if limit was not reached
                                        pointer.consume()
                                    }

                                } else {
                                    // Release gesture
                                    isCurrentGestureCaptured = false

                                    // Set scroll state
                                    isDragging = false
                                }
                            }
                        }
                    }
            ) {
                // Sizes
                val graphWidth = size.width
                val graphHeight = size.height
                lastCanvasHeight = graphHeight
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

                // Limit offset
                val maxOffset = 0f
                var minOffset = -(xStep * (data.size - 1) - graphWidth)
                if (minOffset > 0F) {
                    minOffset = 0F
                }
                offsetX = offsetX.coerceIn(minOffset, maxOffset)

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

                // Last note index
                if (data.isNotEmpty() && data.last() > 1F) {
                    lastNoteIndex = linearToNote(data.last()).toInt()
                }

                // Auto Offset Y
                if (lastNoteIndex != autoOffsetNoteIndex) {
                    val lastNoteOffsetY =
                        (lastNoteIndex.toFloat() * horizontalElementHeightProportion
                                * graphHeight) - (graphHeight * 0.5F)
                    val distance = offsetY - lastNoteOffsetY
                    val windowDistance = autoOffsetWindowDistance * graphHeight

                    if (distance > windowDistance) {
                        offsetY = (lastNoteIndex.toFloat() * horizontalElementHeightProportion
                                * graphHeight) - (graphHeight * 0.5F) + windowDistance
                    }
                    if (distance < -windowDistance) {
                        offsetY = (lastNoteIndex.toFloat() * horizontalElementHeightProportion
                                * graphHeight) - (graphHeight * 0.5F) - windowDistance
                    }
                    autoOffsetNoteIndex = lastNoteIndex
                }

                // Draw
                clipRect(left = 0f, top = 0f, right = graphWidth, bottom = graphHeight) {

                    // ===== Draw horizontal elements ===== //
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

                        // Calculate base
                        var x = i * xStep + offsetX
                        val value = linearToNote(data[i])
                        var y = (graphHeight - (value * (
                                horizontalElementHeightProportion * graphHeight))) + offsetY

                        // Apply limit (no draw beyond graph)
                        if (data[i] < 0F) {
                            // Skip
                            continue
                        }

                        // Combine average value
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
                            val text = (xLabelMin + (xLabelMax - xLabelMin) *
                                    (i / data.size)).toString()
                            val textY = graphHeight - (textLayoutResult.size.height * 1.25F)

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
                        if (lastX in 0f..graphWidth || x in 0f..graphWidth) {
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

                    // ===== Draw vertical elements ===== //
                    for (i in 0..notesCount) {

                        // Calculate
                        val y = (graphHeight - (
                                horizontalElementHeightProportion * graphHeight * i)) + offsetY
                        val elementHeight = horizontalElementHeightProportion * graphHeight

                        // Draw lines and note place
                        if (y in 0F..graphHeight + elementHeight) {

                            drawLine(
                                color = gridColor,
                                start = Offset(0f, y),
                                end = Offset(graphWidth, y),
                                strokeWidth = 1f
                            )

                            val rectColor = if (i == lastNoteIndex)
                                ColorPalette.getSoftGreenColor() else ColorPalette.getSoftRedColor()

                            drawRect(
                                color = rectColor,
                                topLeft = Offset(0f, y),
                                size = Size(
                                    width = levelLabelWidthProportion * graphHeight,
                                    height = -elementHeight
                                )
                            )
                        }

                        // Text
                        val textX = ((levelLabelWidthProportion * graphHeight) -
                                textLayoutResult.size.width.toFloat()) * 0.5F
                        val textY = y - ((elementHeight + textLayoutResult.size.height) * 0.5F)

                        if (textX in 0F..graphWidth && textY in 0F..graphHeight) {
                            drawText(
                                text = getNoteName(i),
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

                    DynamicText(
                        text = "Zoom",
                        modelData = modelData,
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