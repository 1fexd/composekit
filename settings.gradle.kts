@file:Suppress("UnstableApiUsage")

import com.gitlab.grrfe.gradlebuild.maybeConfigureIncludingRootRefreshVersions
import fe.build.dependencies.Grrfe
import fe.buildsettings.config.MavenRepository
import fe.buildsettings.config.configureRepositories
import fe.buildsettings.extension.hasJitpackEnv
import fe.buildsettings.extension.includeProject

rootProject.name = "composekit"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }

    plugins {
        id("de.fayard.refreshVersions") version "0.60.6"
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
        id("net.nemerosa.versioning")
        id("com.android.library")
        id("org.jetbrains.kotlin.android")
    }

    when (val gradleBuildDir = extra.properties["gradle.build.dir"]) {
        null -> {
            val gradleBuildVersion = extra.properties["gradle.build.version"]
            val plugins = extra.properties["gradle.build.plugins"]
                .toString().trim().split(",")
                .map { it.trim().split("=") }
                .filter { it.size == 2 }
                .associate { it[0] to it[1] }

            resolutionStrategy {
                eachPlugin {
                    plugins[requested.id.id]?.let { useModule("$it:$gradleBuildVersion") }
                }
            }
        }

        else -> includeBuild(gradleBuildDir.toString())
    }
}

plugins {
    id("de.fayard.refreshVersions")
    id("org.gradle.toolchains.foojay-resolver-convention")
    id("com.gitlab.grrfe.build-settings-plugin")
}

configureRepositories(
    MavenRepository.Google,
    MavenRepository.MavenCentral,
    MavenRepository.Jitpack,
    MavenRepository.Mozilla,
    MavenRepository("https://oss.sonatype.org/content/repositories/snapshots"),
    mode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
)

maybeConfigureIncludingRootRefreshVersions()

extra.properties["gradle.build.dir"]
    ?.let { includeBuild(it.toString()) }

buildSettings {
    substitutes {
        trySubstitute(Grrfe.std, properties["kotlin-ext.dir"])
    }

    projects("modules") {
        includeProject(":compose-core", "compose/core")
        includeProject(":compose-component", "compose/component")
        includeProject(":compose-m3compat", "compose/m3compat")
        includeProject(":compose-layout", "compose/layout")
        includeProject(":compose-app", "compose/app")
        includeProject(":compose-dialog", "compose/dialog")
        includeProject(":compose-route", "compose/route")

        includeProject(":compose-theme-core", "compose/theme-core")
        includeProject(":compose-theme-preference", "compose/theme-preference")

        includeProject(":core", "core")
        includeProject(":koin", "koin")
        includeProject(":process", "process")
        includeProject(":intent", "intent")

        includeProject(":test-core", "test/core")
        includeProject(":test-compose", "test/compose")

        includeProject(":lifecycle-compose", "lifecycle/compose")
        includeProject(":lifecycle-core", "lifecycle/core")
        includeProject(":lifecycle-koin", "lifecycle/koin")
        includeProject(":lifecycle-network-core", "lifecycle/network/core")
        includeProject(":lifecycle-network-koin", "lifecycle/network/koin")

        includeProject(":span-core", "span/core")
        includeProject(":span-compose", "span/compose")

        includeProject(":preference-core", "preference/core")
        includeProject(":preference-compose-core", "preference/compose-core")
        includeProject(":preference-compose-core2", "preference/compose-core2")
        includeProject(":preference-compose-mock", "preference/compose-mock")

        includeProject(":platform", "platform")

        if (!hasJitpackEnv) {
            includeProject(":compose-test-app", "compose/test-app")
            includeProject(":lifecycle-test-app", "lifecycle/test-app")
            includeProject(":span-test-app", "span/test-app")
            includeProject(":preference-test-app", "preference/test-app")
        }
    }
}
