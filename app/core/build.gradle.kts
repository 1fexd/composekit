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

    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler)
    }

    kotlin {
        jvmToolchain(Version.JVM)
    }

    dependencies {
        implementation(project(":theme-core"))

        implementation(platform(AndroidX.compose.bom))
        implementation(AndroidX.activity.compose)
        implementation(Koin.compose)


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
    group.toString(),
    versioning.info.tag ?: versioning.info.full,
    PublicationComponent.RELEASE
)
