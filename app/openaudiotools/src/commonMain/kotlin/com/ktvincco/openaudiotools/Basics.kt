package com.ktvincco.openaudiotools

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.ktvincco.openaudiotools.data.Database
import com.ktvincco.openaudiotools.presentation.ModelData
import kotlin.math.log10
import kotlin.math.min


fun map(source: Float, minSource: Float, maxSource: Float,
                minTarget: Float, maxTarget: Float): Float {
    val s = (source - minSource) / (maxSource - minSource)
    return minTarget + (maxTarget - minTarget) * s
}


fun amplitudeToDecibels(amplitude: Float, epsilon: Float = 1e-6f): Float {
    return 20 * log10(amplitude + epsilon)
}


fun normalizeDecibels(decibel: Float, minDb: Float = -120f, maxDb: Float = 0f): Float {
    return (decibel - minDb) / (maxDb - minDb)
}


fun resampleAudioData(input: FloatArray, sourceRate: Int, targetRate: Int): FloatArray {
    val ratio = targetRate.toDouble() / sourceRate
    val outputLength = (input.size * ratio).toInt()
    val output = FloatArray(outputLength)

    for (i in output.indices) {
        val srcIndex = i / ratio
        val i0 = srcIndex.toInt()
        val i1 = min(i0 + 1, input.lastIndex)
        val t = srcIndex - i0

        output[i] = (1 - t).toFloat() * input[i0] + t.toFloat() * input[i1]
    }

    return output
}


fun getPlasmaColor(value: Float): Color {
    val plasmaColors = arrayOf(
        floatArrayOf(0.2f, 0.0f, 0.4f),  // Purple
        floatArrayOf(0.8f, 0.2f, 0.9f),  // Pink
        floatArrayOf(1.0f, 0.9f, 0.0f),  // Yellow
        floatArrayOf(1.0f, 1.0f, 1.0f)   // White
    )
    val i = (value * (plasmaColors.size - 1)).toInt()
    val t = value * (plasmaColors.size - 1) - i
    val c1 = plasmaColors[i]
    val c2 = plasmaColors[minOf(i + 1, plasmaColors.size - 1)]

    val r = c1[0] + t * (c2[0] - c1[0])
    val g = c1[1] + t * (c2[1] - c1[1])
    val b = c1[2] + t * (c2[2] - c1[2])

    return Color(r, g, b)
}


// String with the text that can be translated
@Composable
fun dynamicTextString(textString: String, modelData: ModelData): String {
    val languageCode = modelData.languageCode.collectAsState().value
    return Dictionary.getTextTranslation(textString, languageCode) ?: textString
}


@Composable
fun DynamicText(
    text: String = "",
    textByParts: List<String> = listOf(),
    modelData: ModelData,
    isTranslatable: Boolean = true,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
) {

    // Add text to text pasts as the first element
    val parts = arrayOf(text) + textByParts

    // Translate
    if (isTranslatable) {
        // Get language code
        val languageCode = modelData.languageCode.collectAsState().value

        // Get translation for each part
        for (i in parts.indices) {
            val translation = Dictionary.getTextTranslation(parts[i], languageCode)
            if (translation != null) {
                parts[i] = translation
            }
        }
    }

    // Join text
    val joinedText = parts.joinToString("")

    // Draw text
    Text(
        text = joinedText,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow= overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style
    )
}