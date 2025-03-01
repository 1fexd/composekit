@file:Suppress("UnstableApiUsage")

import fe.build.dependencies._1fexd
import fe.buildsettings.extension.hasJitpackEnv
import fe.buildsettings.extension.maybeResolveIncludingRootContext

rootProject.name = "composekit"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }

    plugins {
        id("de.fayard.refreshVersions") version "0.60.5"
        id("com.android.library")
        id("org.jetbrains.kotlin.android")
        id("net.nemerosa.versioning") version "3.1.0"
    }

    when (val gradleBuildDir = extra.properties["gradle.build.dir"]) {
        null -> {
            val gradleBuildVersion = extra.properties["gradle.build.version"]
            val plugins = mapOf(
                "com.gitlab.grrfe.build-settings-plugin" to "com.gitlab.grrfe.gradle-build:build-settings",
                "com.gitlab.grrfe.build-logic-plugin" to "com.gitlab.grrfe.gradle-build:build-logic"
            )

            resolutionStrategy {
                eachPlugin {
                    plugins[requested.id.id]?.let { useModule("$it:$gradleBuildVersion") }
                }
            }
        }

        else -> {
            logger.info("Included build $gradleBuildDir")
            includeBuild(gradleBuildDir.toString())
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

//maybeResolveIncludingRootContext()?.rootProject {
//    refreshVersions {
//        versionsPropertiesFile = rootDir.resolve("versions.properties")
//        logger.info("Using versions file from $versionsPropertiesFile")
//    }
//}

include(":core")
include(":component")
include(":layout")

include(":app:app-core")

include(":dialog:dialog-core")
include(":dialog:dialog-test-app")
include(":route:route-core")

include(":theme:theme-core")
include(":theme:theme-preference")

include(":platform")

if (!hasJitpackEnv) {
    include(":test-app")
}

buildSettings {
    substitutes {
        trySubstitute(_1fexd.droidKit, properties["droidkit.dir"])
    }
}

