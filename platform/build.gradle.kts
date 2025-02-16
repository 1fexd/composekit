plugins {
    `java-platform`
}

dependencies {
    constraints {
        dependencies().forEach { api(it.toDependencyNotation()) }
    }
}

fun Project.toDependencyNotation(): String {
    return "$group:${name}:${version}"
}

fun Project.dependencies(): List<Project> {
    return rootProject.allprojects.filter { it != rootProject && it != project }
}

tasks.create("printArtiacts") {
    dependencies().sorted().forEach {
        println(it.toDependencyNotation())
    }
}
