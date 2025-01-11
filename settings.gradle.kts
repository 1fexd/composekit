@file:Suppress("UnstableApiUsage")

import java.util.*
import kotlin.experimental.ExperimentalTypeInference

rootProject.name = "composekit"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("de.fayard.refreshVersions")
        id("com.android.library")
        id("org.jetbrains.kotlin.android")
        id("net.nemerosa.versioning")
    }

//    resolutionStrategy {
//        eachPlugin {
//            if (requested.id.id == "com.gitlab.grrfe.common-gradle-plugin") {
//                useModule("${requested.id.id}:library:0.0.39")
//            }
//        }
//    }
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

include(":platform")

fun substitute(directory: Any, dependency: String, substitutes: Map<String, String>) {
    includeBuild(directory) {
        dependencySubstitution {
            for ((artifact, project) in substitutes) {
                substitute(module("$dependency:$artifact")).using(project(":$project"))
            }
        }
    }
}

@OptIn(ExperimentalTypeInference::class)
fun Any?.trySubstitute(dependency: String, @BuilderInference builderAction: MutableMap<String, String>.() -> Unit = {}) {
    this?.let { substitute(this, dependency, buildMap(builderAction)) }
}


fun hasEnv(name: String): Boolean {
    return System.getenv(name)?.toBooleanStrictOrNull() == true
}


val isCI = hasEnv("CI")
val isJitPack = hasEnv("JITPACK")
val dev = true

val substitutes = file("local.properties")
if (dev && (substitutes.exists() && !isCI && !isJitPack)) {
    include(":test-app")

    val properties = Properties().apply {
        file("local.properties").reader().use { load(it) }
    }
}


