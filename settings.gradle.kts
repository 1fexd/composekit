@file:Suppress("UnstableApiUsage")

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

maybeResolveIncludingRootContext()?.rootProject {
    refreshVersions {
        versionsPropertiesFile = rootDir.resolve("versions.properties")
        logger.info("Using versions file from $versionsPropertiesFile")
    }
}

fun includeProject(name: String, path: String) {
    include(name)
    project(name).projectDir = file(path)
}

includeProject(":compose-core", "compose/core")
includeProject(":compose-component", "compose/component")
includeProject(":compose-layout", "compose/layout")
includeProject(":compose-app", "compose/app")
includeProject(":compose-dialog", "compose/dialog")
includeProject(":compose-route", "compose/route")

includeProject(":compose-theme-core", "compose/theme-core")
includeProject(":compose-theme-preference", "compose/theme-preference")

includeProject(":core", "core")
includeProject(":koin", "koin")
includeProject(":process", "process")

includeProject(":test-core", "test/core")
includeProject(":test-compose", "test/compose")

includeProject(":lifecycle-core", "lifecycle/core")
includeProject(":lifecycle-koin", "lifecycle/koin")

includeProject(":span-core", "span/core")
includeProject(":span-compose", "span/compose")

includeProject(":preference-core", "preference/core")
includeProject(":preference-compose-core", "preference/compose-core")
includeProject(":preference-compose-core2", "preference/compose-core2")
includeProject(":preference-compose-mock", "preference/compose-mock")

include(":platform")

if (!hasJitpackEnv) {
    includeProject(":compose-test-app", "compose/test-app")
    includeProject(":compose-dialog-test-app", "compose/dialog-test-app")
    includeProject(":lifecycle-test-app", "lifecycle/test-app")
    includeProject(":span-test-app", "span/test-app")
    includeProject(":preference-test-app", "preference/test-app")
}

//buildSettings {
//    substitutes {
//        trySubstitute(_1fexd.droidKit, properties["droidkit.dir"])
//    }
//}

