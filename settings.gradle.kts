@file:Suppress("UnstableApiUsage")

import fe.build.dependencies._1fexd
import fe.buildsettings.extension.hasJitpackEnv
import fe.buildsettings.trySubstitute
import java.util.*
import kotlin.experimental.ExperimentalTypeInference

rootProject.name = "composekit"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    extra.properties["gradle.build.dir"]
        ?.let { includeBuild(it.toString()) }

    plugins {
        id("de.fayard.refreshVersions") version "0.60.5"
        id("com.android.library")
        id("org.jetbrains.kotlin.android")
        id("net.nemerosa.versioning") version "3.1.0"
    }

    resolutionStrategy {
        eachPlugin {
            if (extra.properties["gradle.build.dir"] == null) {
                when (requested.id.id) {
                    "com.gitlab.grrfe.build-settings-plugin" -> useModule("com.gitlab.grrfe.gradle-build:build-settings:0.0.7")
                    "com.gitlab.grrfe.build-logic-plugin" -> useModule("com.gitlab.grrfe.gradle-build:build-logic:0.0.7")
                }
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.mozilla.org/maven2") }
    }
}

plugins {
    id("de.fayard.refreshVersions")
    id("com.gitlab.grrfe.build-settings-plugin")
}

extra.properties["gradle.build.dir"]
    ?.let { includeBuild(it.toString()) }

include(":core")
include(":component")
include(":layout")

include(":app:app-core")

include(":theme:theme-core")
include(":theme:theme-preference")

include(":platform")

if (!hasJitpackEnv) {
    include(":test-app")
}

buildSettings {
    fromFile(file("local.properties")) {
        trySubstitute(_1fexd.android.span, properties["android-span-helper.dir"])
    }
}

