@file:Suppress("UnstableApiUsage")

import fe.buildsettings.extension.hasJitpackEnv

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
        else -> includeBuild(gradleBuildDir.toString())
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
//
include(":compose:core")
include(":compose:component")
include(":compose:layout")

include(":compose:app:app-core")

include(":compose:dialog:dialog-core")
include(":compose:route:route-core")

include(":compose:theme:theme-core")
include(":compose:theme:theme-preference")

include(":core")
include(":koin")

include(":lifecycle:lifecycle-core")
include(":lifecycle:lifecycle-koin")

include(":span:span-core")
include(":span:span-compose")

include(":preference:preference-core")
include(":preference:preference-compose:preference-compose-core")
include(":preference:preference-compose:preference-compose-mock")

include(":platform")

if (!hasJitpackEnv) {
    include(":compose:test-app")
    include(":compose:dialog:dialog-test-app")
    include(":lifecycle:lifecycle-test-app")
    include(":span:span-test-app")
    include(":preference:preference-test-app")
}

//buildSettings {
//    substitutes {
//        trySubstitute(_1fexd.droidKit, properties["droidkit.dir"])
//    }
//}

