package com.ktvincco.openaudiorecorder.data.sound_processing_algorithms

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.log2
import kotlin.math.sin
import kotlin.math.sqrt


data class Complex(val real: Double, val imaginary: Double) {
    operator fun plus(other: Complex) = Complex(real + other.real, imaginary + other.imaginary)
    operator fun minus(other: Complex) = Complex(real - other.real, imaginary - other.imaginary)
    operator fun times(other: Complex): Complex {
        return Complex(
            real * other.real - imaginary * other.imaginary,
            real * other.imaginary + imaginary * other.real
        )
    }

    companion object {
        fun fromPolar(r: Double, theta: Double): Complex {
            return Complex(r * cos(theta), r * sin(theta))
        }
    }
}


private fun fft(input: Array<Complex>): Array<Complex> {
    val n = input.size
    if (n <= 1) return input

    val even = fft(input.filterIndexed { index, _ -> index % 2 == 0 }.toTypedArray())
    val odd = fft(input.filterIndexed { index, _ -> index % 2 != 0 }.toTypedArray())

    val result = Array(n) { Complex(0.0, 0.0) }
    for (k in 0 until n / 2) {
        val t = odd[k] * Complex.fromPolar(1.0, -2 * PI * k / n)
        result[k] = even[k] + t
        result[k + n / 2] = even[k] - t
    }
    return result
}


fun fastFourierTransform(input: FloatArray): List<Float> {
    if (input.isEmpty()) return listOf(0f)
    if (input.size == 1) return listOf(input[0])

    val n = input.size

    // Cut to nearest power of 2
    val nearestPowerOfTwo = 1 shl (floor(log2(n.toFloat())).toInt())
    val trimmedInput = input.copyOf(nearestPowerOfTwo)

    val complexInput = Array(trimmedInput.size) { Complex(trimmedInput[it].toDouble(), 0.0) }
    val fftResult = fft(complexInput)

    // Normalize and consider only positive frequencies
    val scaleFactor = 2.0 / fftResult.size
    return fftResult.take(fftResult.size / 2).map {
        (scaleFactor * sqrt(it.real * it.real + it.imaginary * it.imaginary)).toFloat()
    }
}