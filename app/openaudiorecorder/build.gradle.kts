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
}

// Settings
val appName = "OpenAudioRecorder"

// Configs
val version = "1.8.2" // == CHANGE BEFORE RELEASE (1/2) == //
val androidVersionCode = 12 // == CHANGE BEFORE RELEASE (2/2) == //

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
        moduleName = "openaudiorecorder"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "openaudiorecorder.js"
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
                implementation(project(":common:openaudiotools"))
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
                implementation(project(":common:openaudiotools"))
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
                implementation(project(":common:openaudiotools"))
            }
        }
        /*val wasmJsMain by getting {
            dependencies {
                implementation(project(":common:openaudiotools"))
            }
        }*/
    }
}

android {
    namespace = "com.ktvincco.openaudiorecorder"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ktvincco.openaudiorecorder"
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
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        mainClass = "com.ktvincco.openaudiorecorder.MainKt"

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
        val file = file("src/commonMain/kotlin/com/ktvincco/openaudiorecorder/AppInfo.kt")
        file.writeText("""
            package com.ktvincco.openaudiorecorder
            
            object AppInfo {
                const val NAME = "$appName"
                const val VERSION = "$version"
            }
        """.trimIndent())
    }
}
tasks.getByName("preBuild").dependsOn("generateVersion")

