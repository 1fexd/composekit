import fe.buildsrc.Version
import fe.buildsrc.dependency.PinnedVersions
import fe.buildsrc.publishing.PublicationComponent
import fe.buildsrc.publishing.asProvider
import fe.buildsrc.publishing.publish

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("net.nemerosa.versioning")
    `maven-publish`
}

group = "fe.composekit.app.core"

android {
    namespace = group.toString()
    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        minSdk = Version.MIN_SDK
    }

    buildFeatures {
        compose = true
    }

    kotlin {
        jvmToolchain(Version.JVM)
    }

    dependencies {
        implementation(project(":theme-core"))

        implementation(platform(AndroidX.compose.bom))
        implementation(AndroidX.activity.compose)
        implementation(Koin.compose)

        implementation(AndroidX.compose.ui)
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
    group.toString(),
    versioning.asProvider(project),
    PublicationComponent.RELEASE
)
