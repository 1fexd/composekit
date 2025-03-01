import fe.buildlogic.Version

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "fe.android.lifecycle.util.test"
    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        applicationId = "fe.android.lifecycle.util.test"
        minSdk = 25
        targetSdk = Version.COMPILE_SDK
        compileSdk = Version.COMPILE_SDK
        versionCode = (System.currentTimeMillis() / 1000).toInt()
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlin {
        jvmToolchain(Version.JVM)
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":lifecycle:lifecycle-core"))

    implementation(platform(AndroidX.compose.bom))
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.graphics)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material3)

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.runtime.ktx)
    implementation(AndroidX.lifecycle.runtime)
    implementation(AndroidX.lifecycle.process)
    implementation(AndroidX.lifecycle.common)

    implementation(AndroidX.activity.compose)
}
