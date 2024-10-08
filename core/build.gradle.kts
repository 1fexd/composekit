import fe.buildsrc.Version
import fe.buildsrc.dependency.PinnedVersions
import fe.buildsrc.dependency._1fexd
import fe.buildsrc.publishing.PublicationComponent
import fe.buildsrc.publishing.publish
import fe.buildsrc.publishing.asProvider

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20"
    id("net.nemerosa.versioning")
//    id("fe.gradle.tools")
    `maven-publish`
}

group = "fe.composekit.core"

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
        implementation(platform(AndroidX.compose.bom))
        implementation(PinnedVersions.ComposeUi)
        implementation(PinnedVersions.Material3)

        implementation(_1fexd.android.span.compose)
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
