rootProject.name = "composekit"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("de.fayard.refreshVersions") version "0.60.5"
        id("com.android.library")
        id("org.jetbrains.kotlin.android")
        id("net.nemerosa.versioning")
    }
}

@Suppress("UnstableApiUsage")
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
    id("de.fayard.refreshVersions") version "0.60.5"
}

fun includeProject(path: String, filePath: String, name: String) {
    include(path)

    val project = project(path)
    project.projectDir = file(filePath)
    project.name = name
}

includeProject(":core", "app/core", "app-core")

includeProject(":core", "theme/core", "theme-core")
includeProject(":preference", "theme/preference", "theme-preference")

include(":core")
include(":component")
include(":layout")

val isJitPack = System.getenv("JITPACK")?.toBooleanStrictOrNull() == true

if (!isJitPack) {
    include(":test-app")
}
