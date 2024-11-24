import fe.buildsrc.Version
import fe.buildsrc.dependency.PinnedVersions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("net.nemerosa.versioning")
}

group = "fe.composekit.testapp"

android {
    namespace = group.toString()
    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        applicationId = group.toString()
        minSdk = Version.MIN_SDK
        targetSdk = Version.COMPILE_SDK
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

    buildFeatures {
        compose = true
    }

    kotlin {
        jvmToolchain(Version.JVM)
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    dependencies {
        implementation(project(":component"))
        implementation(project(":core"))
        implementation(project(":layout"))

        implementation(platform(AndroidX.compose.bom))
        implementation(AndroidX.compose.ui.withVersion(PinnedVersions.ComposeVersion))
        implementation(AndroidX.compose.ui.graphics)
        implementation(AndroidX.compose.ui.toolingPreview)
        implementation(PinnedVersions.Material3)
        implementation(AndroidX.core.ktx)
        implementation(AndroidX.lifecycle.viewModelKtx)
        implementation(AndroidX.lifecycle.runtime.ktx)
        implementation(AndroidX.activity.compose)
        implementation(AndroidX.compose.material.icons.core)
    }
}


