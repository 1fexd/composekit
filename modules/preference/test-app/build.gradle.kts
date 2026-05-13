import com.gitlab.grrfe.gradlebuild.android.AndroidSdk

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
    id("de.infix.testBalloon") version "0.8.4-K2.3.20"
}

group = "fe.android.preference.helper.testapp"

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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    jvmToolchain(com.gitlab.grrfe.gradlebuild.Version.JVM)
}

dependencies {
    implementation(project(":preference-core"))
    implementation(project(":preference-compose-core"))
    implementation(project(":preference-compose-core2"))
    testImplementation("de.infix.testBalloon:testBalloon-framework-core:_")
    testImplementation("de.infix.testBalloon:testBalloon-integration-robolectric:_")
    testImplementation(Testing.junit4)

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.lifecycle.runtime.ktx)
    implementation(AndroidX.activity.compose)
    implementation(platform("androidx.compose:compose-bom-alpha:_"))
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.graphics)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material3)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)
}
