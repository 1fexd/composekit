@file:Suppress("UnstableApiUsage")

import fe.build.dependencies.Grrfe
import fe.buildsettings.config.MavenRepository
import fe.buildsettings.config.configureRepositories
import fe.buildsettings.extension.hasJitpackEnv
import fe.buildsettings.extension.includeProject
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
        id("net.nemerosa.versioning")
        id("com.android.library")
        id("org.jetbrains.kotlin.android")
    }

    when (val gradleBuildDir = extra.properties["gradle.build.dir"]) {
        null -> {
            val gradleBuildVersion = extra.properties["gradle.build.version"]
            val plugins = arrayOf(
                "build-settings-plugin" to "build-settings",
                "build-logic-plugin" to "build-logic",
                "new-build-logic-plugin" to "new-build-logic",
                "library-build-plugin" to "new-build-logic"
            ).associate { (artifact, project) ->
                "com.gitlab.grrfe.$artifact" to "com.gitlab.grrfe.gradle-build:$project"
            }

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
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
    id("de.fayard.refreshVersions")
    id("com.gitlab.grrfe.build-settings-plugin")
}

configureRepositories(
    MavenRepository.Google,
    MavenRepository.MavenCentral,
    MavenRepository.Jitpack,
    MavenRepository.Mozilla,
    mode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
)

extra.properties["gradle.build.dir"]
    ?.let { includeBuild(it.toString()) }

maybeResolveIncludingRootContext()?.rootProject {
    refreshVersions {
        versionsPropertiesFile = rootDir.resolve("versions.properties")
        logger.info("Using versions file from $versionsPropertiesFile")
    }
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
    includeProject(":lifecycle-test-app", "lifecycle/test-app")
    includeProject(":span-test-app", "span/test-app")
    includeProject(":preference-test-app", "preference/test-app")
}

buildSettings {
    substitutes {
        trySubstitute(Grrfe.std, properties["kotlin-ext.dir"])
    }
}
