rootProject.name = "compose-ui-kit"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("de.fayard.refreshVersions") version "0.60.5"
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
        mavenLocal()
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.60.5"
}


include(":components")
include(":util")


val isJitPack = System.getenv("JITPACK")?.toBooleanStrictOrNull() == true

if (!isJitPack) {
    include(":test-app")
}
