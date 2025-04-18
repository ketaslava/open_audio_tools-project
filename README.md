
# Open Voice Analyzer

Android IDE Kotlin Multi Platform Compose UI project

# Links, Developers, Contacts, Licenses

All information here: [Project Headline Repository](https://github.com/ketaslava/open_voice_analyzer)  
= Only technical information in current repository =

# BEFORE RELEASE checklist

1. Check parameters "CHANGE BEFORE RELEASE" in build.gradle.kts in :app:module

# Run (Desktop)

OpenVoiceAnalyzer: $ ./gradlew :app:openvoiceanalyzer:run
OpenAudioRecorder: $ ./gradlew :app:openaudiorecorder:run

# Run (Android Device) (Debug)

OpenVoiceAnalyzer: $ ./gradlew :app:openvoiceanalyzer:installDebug
OpenAudioRecorder: $ ./gradlew :app:openaudiorecorder:installDebug

# Export (Linux)

Deb Package (JVM):
OpenVoiceAnalyzer: $ ./gradlew :app:openvoiceanalyzer:packageDeb
OpenAudioRecorder: $ ./gradlew :app:openaudiorecorder:packageDeb

Rpm Package (JVM):     
OpenVoiceAnalyzer: $ ./gradlew :app:openvoiceanalyzer:packageRpm
OpenAudioRecorder: $ ./gradlew :app:openaudiorecorder:packageRpm

* Output in: /app/"module name"/build/compose/binaries/main/deb/

# Export (Android) (Debug) (APK)

OpenVoiceAnalyzer: $ ./gradlew :app:openvoiceanalyzer:assembleDebug
OpenAudioRecorder: $ ./gradlew :app:openaudiorecorder:assembleDebug

* Output in: app/"module name"/build/outputs/apk/debug/

# Export (Android) (Release) (AAB)

OpenVoiceAnalyzer: $ ./gradlew :app:openvoiceanalyzer:bundleRelease
OpenAudioRecorder: $ ./gradlew :app:openaudiorecorder:bundleRelease

* Output in: app/"module name"/build/outputs/aab/release/
