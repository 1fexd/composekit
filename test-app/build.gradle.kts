import de.fayard.refreshVersions.core.versionFor
import fe.buildsrc.Version

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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

    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler)
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
        implementation(AndroidX.compose.ui)
        implementation(AndroidX.compose.ui.graphics)
        implementation(AndroidX.compose.ui.toolingPreview)
        implementation(AndroidX.compose.material3)

        implementation(AndroidX.core.ktx)
        implementation(AndroidX.lifecycle.viewModelKtx)
        implementation(AndroidX.lifecycle.runtime.ktx)
        implementation(AndroidX.activity.compose)
    }
}


