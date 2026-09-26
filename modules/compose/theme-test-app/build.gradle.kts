import com.gitlab.grrfe.gradlebuild.android.AndroidSdk
import fe.build.dependencies._1fexd

plugins {
    id("com.android.application")
    kotlin("plugin.compose")
    kotlin("plugin.serialization")
}

group = "fe.composekit.theme.testapp"

android {
    namespace = group.toString()
    compileSdk = 37
    compileSdkMinor = 2

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
    implementation(project(":compose-app"))
    implementation(project(":compose-theme-core"))
    implementation(project(":compose-theme-preference"))
    implementation(project(":preference-compose-core"))
    implementation(project(":preference-compose-core2"))
//    implementation(project(":compose-core"))
//    implementation(project(":compose-dialog"))
//    implementation(project(":compose-layout"))
    implementation(project(":koin"))

    implementation(platform("androidx.compose:compose-bom-alpha:_"))
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.graphics)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material3)
    implementation(AndroidX.navigation.compose)
    implementation(AndroidX.navigation.ui)
    androidTestImplementation(AndroidX.navigation.testing)
    implementation(KotlinX.serialization.json)
    implementation(Google.android.material)

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.runtime.ktx)
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.compose.material.icons.core)
}
