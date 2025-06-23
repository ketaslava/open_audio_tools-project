
# OpenAudioTools

Android IDE Kotlin Multi Platform Compose UI project

# Links, Developers, Contacts, Licenses

= Only technical information in this repository =

All app-related information here:

OpenAudioTools: [OAT Headline Repository](https://github.com/ketaslava/open_audio_tools)

# BEFORE RELEASE checklist

1. Check parameters "CHANGE BEFORE RELEASE" in build.gradle.kts in :app:module

# ADDITIONAL BUILD OUTPUT (ANDROID)

mapping.txt: build/outputs/mapping/release/mapping.txt
native debug symbols: build/intermediates/merged_native_debug_symbols/release/out/

# Run (Desktop)

$ ./gradlew run

# Run (Android Device) (Debug)

$ ./gradlew installDebug

# Export (Linux)

Deb Package (JVM):
$ ./gradlew packageDeb

Rpm Package (JVM):     
$ ./gradlew packageRpm

* Output in: /app/openaudiotools/build/compose/binaries/main/deb/

# Export (Android) (Debug) (APK)

$ ./gradlew assembleDebug

* Output in: app/openaudiotools/build/outputs/apk/debug/

# Export (Android) (Release) (AAB)

$ ./gradlew bundleRelease

* Output in: app/openaudiotools/build/outputs/aab/release/
