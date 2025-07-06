import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.ui.geometry.android)
    implementation(libs.androidx.ui.unit.android)
    implementation(libs.androidx.ui.text.android)
    implementation(libs.kotlinx.datetime)
    debugImplementation(compose.uiTooling)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.activity.ktx)
}

// Settings
val appName = "OpenAudioTools"

// Configs
val version = "2.2.0" // == CHANGE BEFORE RELEASE (1/2) == //
val androidVersionCode = 10 // == CHANGE BEFORE RELEASE (2/2) == //

kotlin {

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    /* // DEV // Disabled
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "openaudiotools"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "openaudiotools.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }*/
    
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
            }
        }
        /*val wasmJsMain by getting {
            dependencies {
            }
        }*/
    }
}

android {
    namespace = "com.ktvincco.openaudiotools"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ktvincco.openaudiotools"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = androidVersionCode
        versionName = version
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {

            // Mapping
            isMinifyEnabled = false

            // Debug symbols
            ndk {
                debugSymbolLevel = "FULL"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        mainClass = "com.ktvincco.openaudiotools.MainKt"

        val jvmArgsList = listOf(
            "-XX:+UseG1GC", // "", -XX:+UseG1GC , -XX:+UseZGC // -XX:+UseG1GC
            "-XX:G1PeriodicGCInterval=2000", // 2000
        )

        jvmArgs.addAll(jvmArgsList)

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi,
                TargetFormat.Deb, TargetFormat.Rpm, TargetFormat.AppImage)
            packageName = appName
            packageVersion = version
            jvmArgs.addAll(jvmArgsList)
            linux {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/icon.png"))
            }
        }
    }
}

// Auto update app version in configs
tasks.register("generateVersion") {
    doLast {
        val file = file("src/commonMain/kotlin/com/ktvincco/openaudiotools/AppInfo.kt")
        file.writeText("""
            package com.ktvincco.openaudiotools
            
            object AppInfo {
                const val NAME = "$appName"
                const val VERSION = "$version"
            }
        """.trimIndent())
    }
}
tasks.getByName("preBuild").dependsOn("generateVersion")