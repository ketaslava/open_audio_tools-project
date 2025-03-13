package com.ktvincco.openvoiceanalyzer.data.sound_processing_algorithms


fun calculateVAD(spectrumInHz: FloatArray): Float {

   // Settings
   val harmonicsCount = 8
   val voiceHzMin = 50
   val voiceHzMax = 500
   val threshold = 0.5F

   // Optimization Settings
   val offsetStep = 4
   val gapStep = 4

   // Calculate 1 harmonic as a pitch
   val sumByOffset = FloatArray(voiceHzMax + 1)
   for (offset in voiceHzMin..voiceHzMax step offsetStep) {
      for (gap in voiceHzMin..voiceHzMax step gapStep) {
         for (harmonic in 0..<harmonicsCount) {
            val index = offset + (gap * harmonic)
            if (index < spectrumInHz.size) {
               sumByOffset[offset] += spectrumInHz[index]
            }
         }
      }
   }
   val pitch = sumByOffset.toList().indexOf(sumByOffset.maxOrNull())


   // Apply filter
   val spectrum = applyWindowSmooth(spectrumInHz, pitch / 2, 4)

   // Calculate harmonic value
   var harmonicSum = 0F
   for (harmonic in 1..harmonicsCount) {
      val index = pitch * harmonic
      if (index in spectrum.indices) {
         harmonicSum += spectrum[index]
      }
   }

   // Calculate between harmonic value
   var betweenHarmonicSum = 0F
   for (harmonic in 1..harmonicsCount) {
      val index = (pitch * harmonic) - (pitch / 2)
      if (index in spectrum.indices) {
         betweenHarmonicSum += spectrum[index]
      }
   }

   // Check values
   if (harmonicSum == 0F) { return 0F }
   if (betweenHarmonicSum > harmonicSum) { return 0F }

   val ratio = (1F - (betweenHarmonicSum / harmonicSum))
   //println(ratio.coerceIn(0F, 1F))

   return if (ratio > threshold) 1F else 0F
}