@file:Suppress("UnstableApiUsage")

import com.gitlab.grrfe.gradlebuild.config.configureRepositories
import com.gitlab.grrfe.gradlebuild.extension.hasJitpackEnv
import com.gitlab.grrfe.gradlebuild.maybeConfigureIncludingRootRefreshVersions
import com.gitlab.grrfe.gradlebuild.repository.MavenRepository
import com.gitlab.grrfe.gradlebuild.repository.google
import com.gitlab.grrfe.gradlebuild.repository.jitpack
import com.gitlab.grrfe.gradlebuild.repository.mavenCentral
import com.gitlab.grrfe.gradlebuild.repository.mozilla
import fe.build.dependencies.Grrfe

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
        id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
        id("com.android.library")
        id("org.jetbrains.kotlin.android")
    }

    when (val gradleBuildDir = extra.properties["gradle.build.dir"]) {
        null -> {
            val gradleBuildVersion = extra.properties["gradle.build.version"]
            resolutionStrategy {
                eachPlugin {
                    with(requested.id) {
                        if (namespace == "com.gitlab.grrfe") {
                            useModule("com.gitlab.grrfe.gradle-build:$name:$gradleBuildVersion")
                        }
                    }
                }
            }
        }
        else -> includeBuild(gradleBuildDir.toString())
    }
}

plugins {
    id("de.fayard.refreshVersions")
    id("org.gradle.toolchains.foojay-resolver-convention")
    id("com.gitlab.grrfe.settings-build-plugin")
}

configureRepositories(
    MavenRepository.google(),
    MavenRepository.mavenCentral(),
    MavenRepository.jitpack(),
    MavenRepository.mozilla(),
    MavenRepository("https://oss.sonatype.org/content/repositories/snapshots"),
    mode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
)

maybeConfigureIncludingRootRefreshVersions()

extra.properties["gradle.build.dir"]
    ?.let { includeBuild(it.toString()) }

buildSettings {
    projects("external") {
        includeProject(":ext-mozilla-support-utils", "mozilla-support-utils")
    }

    projects("modules") {
        includeProject(":core", "core")
        includeProject(":koin", "koin")
        includeProject(":process", "process")
        includeProject(":intent", "intent")

        includeProject(":platform", "platform")

        projects("compose") {
            includeProject(":compose-core", "core")
            includeProject(":compose-component", "component")
            includeProject(":compose-m3compat", "m3compat")
            includeProject(":compose-layout", "layout")
            includeProject(":compose-app", "app")
            includeProject(":compose-dialog", "dialog")
            includeProject(":compose-route", "route")

            includeProject(":compose-theme-core", "theme-core")
            includeProject(":compose-theme-preference", "theme-preference")
            if (!hasJitpackEnv) {
                includeProject(":compose-test-app", "test-app")
            }
        }
        projects("test") {
            includeProject(":test-core", "core")
            includeProject(":test-compose", "compose")
        }
        projects("lifecycle") {
            includeProject(":lifecycle-compose", "compose")
            includeProject(":lifecycle-core", "core")
            includeProject(":lifecycle-koin", "koin")
            includeProject(":lifecycle-network-core", "network/core")
            includeProject(":lifecycle-network-koin", "network/koin")
            if (!hasJitpackEnv) {
                includeProject(":lifecycle-test-app", "test-app")
            }
        }
        projects("preference") {
            includeProject(":preference-core", "core")
            includeProject(":preference-compose-core", "compose-core")
            includeProject(":preference-compose-core2", "compose-core2")
            includeProject(":preference-compose-mock", "compose-mock")
            includeProject(":preference-compose-mock2", "compose-mock2")
            includeProject(":preference-util", "util")
            if (!hasJitpackEnv) {
                includeProject(":preference-test-app", "test-app")
            }
        }
        projects("span") {
            includeProject(":span-core", "core")
            includeProject(":span-compose", "compose")
            if (!hasJitpackEnv) {
                includeProject(":span-test-app", "test-app")
            }
        }
    }

    substitutes {
        trySubstitute(Grrfe.std, properties["kotlin-ext.dir"])
    }
}
