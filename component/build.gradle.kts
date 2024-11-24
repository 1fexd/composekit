import fe.buildsrc.Version
import fe.buildsrc.dependency.PinnedVersions
import fe.buildsrc.dependency._1fexd
import fe.buildsrc.publishing.PublicationComponent
import fe.buildsrc.publishing.publish
import fe.buildsrc.publishing.asProvider

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("net.nemerosa.versioning")
    `maven-publish`
}

group = "fe.composekit.component"

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
        implementation(project(":core"))
        implementation(project(":layout"))

        implementation(platform(AndroidX.compose.bom))
        implementation(AndroidX.compose.ui.withVersion(PinnedVersions.ComposeVersion))
        implementation(AndroidX.compose.ui.text)
        implementation(AndroidX.compose.ui.tooling)
        implementation(AndroidX.compose.ui.toolingPreview)
        implementation(AndroidX.compose.material.icons.core)
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
