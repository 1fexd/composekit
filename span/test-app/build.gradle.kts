import com.gitlab.grrfe.gradlebuild.android.AndroidSdk
import fe.build.dependencies.Grrfe
import fe.buildlogic.Version

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "fe.androidspanhelper.testapp"

android {
    namespace = group.toString()
    compileSdk = AndroidSdk.COMPILE_SDK

    defaultConfig {
        applicationId = group.toString()
        minSdk = AndroidSdk.MIN_SDK
        targetSdk = AndroidSdk.COMPILE_SDK
        versionCode = (System.currentTimeMillis() / 1000).toInt()
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testOptions.unitTests.isIncludeAndroidResources = true
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":span-core"))
    implementation(project(":span-compose"))

    implementation(platform(AndroidX.compose.bom))
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.graphics)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material3)
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.runtime.ktx)
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.compose.material.icons.core)
    debugImplementation(AndroidX.compose.ui.tooling)

    testImplementation(Grrfe.std.result.assert)
    testImplementation(Koin.test)
    testImplementation(Koin.junit4)
    testImplementation(Koin.android)
    testImplementation(AndroidX.test.ext.junit)
    testImplementation(AndroidX.test.ext.junit.ktx)
    testImplementation(Testing.junit4)
    testImplementation(Testing.robolectric)
}
