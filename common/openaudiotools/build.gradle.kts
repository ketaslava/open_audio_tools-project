repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

plugins {
    /*alias(libs.plugins.kotlinMultiplatform)
    //alias(libs.plugins.androidApplication)
    id("com.android.library")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)*/
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")      // чтобы заработал DSL compose { … }
    alias(libs.plugins.composeMultiplatform)       // id("org.jetbrains.compose")
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

android {
    namespace = "com.ktvincco.openaudiotools"
    compileSdk = 35

    defaultConfig {
        minSdk = 32
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

kotlin {

    androidTarget {
        /*@OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }*/
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.components.resources)  // <<-- библиотека ресурсов
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(libs.androidx.activity.compose)
            }
        }
        /*val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }*/
    }

    /*listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    jvm("desktop")
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
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
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }*/
}

/*compose.desktop {
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
            packageName = "Open Voice Analyzer"
            packageVersion = version
            jvmArgs.addAll(jvmArgsList)
            linux {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/icon.png"))
            }
        }
    }
}*/