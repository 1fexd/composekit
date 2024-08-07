import de.fayard.refreshVersions.core.versionFor
import fe.buildsrc.dependency.PinnedVersions
import fe.buildsrc.Version
import fe.buildsrc.publishing.PublicationComponent
import fe.buildsrc.publishing.publish

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("net.nemerosa.versioning")
    `maven-publish`
}

val group = "fe.android.compose.ui.kit"

android {
    namespace = group
    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        minSdk = Version.MIN_SDK
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

    dependencies {
        implementation(platform(AndroidX.compose.bom))
        implementation(PinnedVersions.ComposeUi)
        implementation(PinnedVersions.Material3)
    }

    publishing {
        multipleVariants {
            allVariants()
            withSourcesJar()
        }
    }
}

publishing.publish(
    project,
    group,
    versioning.info.tag ?: versioning.info.full,
    PublicationComponent.RELEASE
)
